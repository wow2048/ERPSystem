<template>
  <layout>
    <Title title="员工打卡"></Title>
    <div style="margin-top: 10px">
      <el-table
          :data="staffList"
          stripe
          style="width: 100%"
          :header-cell-style="{'text-align':'center'}"
          :cell-style="{'text-align':'center'}">
        <el-table-column
            prop="id"
            label="id"
            width="150">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="150">
        </el-table-column>
        <el-table-column
            prop="type"
            label="工作岗位"
            width="100"
            :filters="[{ text: '库存管理人员', value: '库存管理人员'}, { text: '进货销售人员', value: '进货销售人员'}, {text: '销售经理',value:'销售经理'},{text:'财务人员',value:'财务人员'},{text:'人力资源人员',value:'人力资源人员'},{text:'总经理',value:'总经理'}]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag

                disable-transitions>{{scope.row.type}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
            prop="timesLast"
            label="上个月打卡次数"
            width="150">
        </el-table-column>
        <el-table-column
            prop="times"
            label="本月打卡次数"
            width="150">
        </el-table-column>
        <el-table-column
            prop="checkRecord"
            label="最近打卡记录"
            width="150"
            :formatter="formatDate">
        </el-table-column>
        <el-table-column
          label="今日打卡">
        <template slot-scope="scope">
          <el-button
              @click.native.prevent="checkIn(scope.row.id)"
              type="danger"
              size="small"
              v-if="formatDate2(scope.row.checkRecord) != getCurrentDate()">
            打卡
          </el-button>
          <el-button
              type= info
              size="small"
              v-if="formatDate2(scope.row.checkRecord) == getCurrentDate()">
            已打卡
          </el-button>
        </template>
        </el-table-column>
      </el-table>
    </div>
  </layout>
</template>

<script>
import Layout from "@/components/content/Layout";
import Title from "@/components/content/Title";
import {createCheckIn, findIdCheckInThisMonth, getAllCheckLatest, getAllStaffById,findIdCheckInLastMonth} from "@/network/staff";

export default {
  name: "StaffCheckIn",
  components: {Title, Layout},
  data(){
    return{

      staffList: [],
      checkList: [],
      typeList: ['库存管理人员', '进货销售人员','销售经理','财务人员','人力资源人员','总经理'],
      checkInVisible : false,
      oneCheckIn: [],
      oneLastCheckIn:[],

    }
  },
  mounted: async function () {
    const that = this;
    await getAllStaffById().then(_res => {
      that.staffList = that.staffList.concat(_res.result)
      for(let i = 0;i<this.staffList.length;i++){
        if(this.staffList[i].type == "总经理") this.staffList.splice(i,1);

      }
    })
    await getAllCheckLatest().then(_res => {
      that.checkList = that.checkList.concat(_res.result)
    })
    await findIdCheckInThisMonth().then(_res=>{
      this.oneCheckIn = this.oneCheckIn.concat(_res.result)
    })
    await findIdCheckInLastMonth().then(_res=>{
      this.oneLastCheckIn = this.oneLastCheckIn.concat(_res.result)
    })
    await this.addCheckInToColumn()
  },

  methods: {
    checkIn(id){
      let date = this.getFullCurrentDate();
      let checkInObj = {}
      checkInObj.id = id;
      checkInObj.checkRecord = date
      createCheckIn(checkInObj).then(_res=>{

          })
      for(let i = 0;i<this.staffList.length;i++){
        if(this.staffList[i].id == id){
          this.staffList[i].checkRecord = date;
          ++this.staffList[i].times;
        }
        this.$set(this.staffList,i,this.staffList[i])
      }
    },

    addCheckInToColumn(){

      for(let i = 0; i<this.staffList.length;i++){
        for(let j = 0;j<this.checkList.length;j++){
          if(this.staffList[i].id === this.checkList[j].id){
            this.staffList[i].checkRecord = this.checkList[j].checkRecord;
            this.$set(this.staffList,i,this.staffList[i])
          }
          if(this.staffList[i].checkRecord == undefined) this.staffList[i].checkRecord = null;
        }

        for(let k = 0;k<this.oneCheckIn.length;k++){
          if(this.staffList[i].id===this.oneCheckIn[k].id){
            this.staffList[i].times = this.oneCheckIn[k].times;
            this.$set(this.staffList,i,this.staffList[i])
          }
          if(this.staffList[i].times==undefined) this.staffList[i].times = 0;
        }

        for(let k = 0;k<this.oneLastCheckIn.length;k++){
          if(this.staffList[i].id===this.oneLastCheckIn[k].id){
            this.staffList[i].timesLast = this.oneLastCheckIn[k].times;
            this.$set(this.staffList,i,this.staffList[i])
          }
          if(this.staffList[i].timesLast==undefined) this.staffList[i].timesLast = 0;
        }

      }
      console.log(this.staffList);
    },
    formatDate(row,column){
      let data = row[column.property]
      if(data == null){
        return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate();
    },
    formatDate2(date){
      let dt = new Date(date)
      return dt.getFullYear() + '-' + (dt.getMonth()+1) + '-' + dt.getDate();
    },
    async reload(){
      await getAllStaffById().then(_res=>{
        this.staffList = this.staffList.concat(_res.result)
      })
    },
    filterTag(value, row) {
      return row.type === value
    },

    close() {
      this.staffList = [];
      this.reload();
    },
    getCurrentDate() {
      let now = new Date();
      let year = now.getFullYear(); //得到年份
      let month = now.getMonth(); //得到月份
      let date = now.getDate(); //得到日期
      month = month + 1;
      //if (month < 10) month = "0" + month;
      //if (date < 10) date = "0" + date;
      let time = "";
      //精确到天
      time = year + "-" + month + "-" + date;

      return time;
    },

    getFullCurrentDate() {
      let now = new Date();
      let year = now.getFullYear(); //得到年份
      let month = now.getMonth(); //得到月份
      let date = now.getDate(); //得到日期
      month = month + 1;
      if (month < 10) month = "0" + month;
      if (date < 10) date = "0" + date;
      let time = "";
      //精确到天
      time = year + "-" + month + "-" + date;

      return time;
    }

  }
}
</script>

<style scoped>

</style>