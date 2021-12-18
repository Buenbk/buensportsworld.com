<template>
  <div class='wrap' id="app">
    <template v-if='!isPageFlag'>
      <div class="mask" v-if='isMaskFlag'></div>
      <div class="alert-wrap" :class="{'alert-active':isActiveClass}" v-if='isMaskFlag'>
        <div class="close" @click='close'>X</div>
        <img src="../../static/images/blank.png" v-if='type == 0'>
        <img src="../../static/images/Durant.png" v-if='type == 1'>
        <img src="../../static/images/Curry.png" v-if='type == 2'>
        <img src="../../static/images/James.png" v-if='type == 3'>
        <img src="../../static/images/Zimuge.png" v-if='type == 4'>
        <img src="../../static/images/zq.png" v-if='type == 5'>
      </div>

      <div class='info-link' @click='isPageFlag = true'>NBA World(sorry，此界面功能尚未开发完全)</div>
      <h3 class="card-title">抽取球星卡</h3>

      <ul class="card-list">
        <li v-for='item in list' :style="{'height':imgHeight}">
          <template v-if='item.nums <= 0'>
            <img src="../../static/images/blank.png" v-if='!item.isEmpty'>
          </template>
          <template v-else>
            <div class="font">
              <div class="nums">{{item.nums}}</div>
              <img :src="item.src">
            </div>
            <div class="back" :style="{'height':imgHeight}">
              <h3 class="tc">{{item.name}}</h3>
              {{item.info}}
            </div>
          </template>
        </li>
        <li v-if='type == 5' :style="{'height':imgHeight}">
          <div class="font">
            <img src="../../static/images/ql.png">
          </div>
          <div class="back" :style="{height:imgHeight}">
            <h3 class="tc"></h3>
          </div>
        </li>
      </ul>
      <center>
        <div class="btn" @click='choose' v-if='!isAllFlag'>抽卡({{times}})</div>
        <div class="btn" @click='compose' v-else>合成</div>
      </center>
      <center class='anxiang'>&nbsp;</center>
      <div class="btn" @click='addTimes'>增加次数</div>
      <div class="btn" @click='addChance'>+稀缺卡概率({{rate}})</div>
      <div class="btn" @click='sendXW'>送特殊卡</div>
      <div class="btn" @click='window.location.reload()'>重置</div>
      <br>
    </template>
    <template v-else>

      <br>
      <center><div class="btn" @click='isPageFlag = false'>返回</div></center>
    </template>

  </div>
</template>

<script>
export default {
  name: "nbaWorld.vue",
  /*el:'#app',*/
  mounted:function(){
    alert("sorry，此界面功能尚未开发完全");
    var that=this;
    setTimeout(function(){
      that.imgHeight = document.querySelector(".card-list li img").height+'px';
    }, 200);
  },
  data() {
    return{
      imgHeight:'155px',
      isPageFlag:false,
      isActiveClass:false,
      isMaskFlag:false,
      isAllFlag:false,
      type:0,
      times:10,
      chance:100,
      rate:'2%',
      list:[
        {src:'../../static/images/Durant.png',nums:0,name:'Durant',info:'1'},
        {src:'../../static/images/Curry.png',nums:0,name:'Curry',info:'2'},
        {src:'../../static/images/James.png',nums:0,name:'James',info:'3'},
        {src:'../../static/images/Zimuge.png',nums:0,name:'Zimuge',info:'4'}
      ]
    }
  },
  methods:{
    choose:function(){//抽卡
      var that=this;
      var randomNums;
      if(that.times > 0){
        randomNums = Math.floor(Math.random()*that.chance);
        if(randomNums >= 0 && randomNums < 24){
          that.type = 0;
        }else if(randomNums >= 24 && randomNums < 48){
          that.type = 1;
        }else if(randomNums >= 48 && randomNums < 72){
          that.type = 2;
        }else if(randomNums >= 72 && randomNums < 98){
          that.type = 3;
        }else if(randomNums >= 98 && randomNums < that.chance){
          that.type = 4;
        }
        that.isMaskFlag=true;
        if(that.type != 0){
          that.list[that.type - 1].nums++;
        }
        that.isAll();
        that.times--;
      }else{
        alert('今天抽卡次数已经用完，请明天再试！');
      }
    },
    addTimes:function(){//增加抽卡数
      //this.times = this.times + 5;
      this.times = 5;
    },
    sendXW:function(){//送卡
      this.list[3].nums++;
      this.isAll();
    },
    isAll:function(){//判断卡片是否已经集满
      var that=this;
      var flag=true;
      for(var i=0;i<4;i++){
        if(that.list[i].nums == 0){
          flag=false;
          break;
        }
      }
      if(flag){
        that.isAllFlag=true;
      }
    },
    compose:function(){//合成祥瑞
      var that=this;
      if(that.type == 5){
        alert('已经合成过一次了，不能再合成！');
      }else{
        for(var i=0;i<4;i++){
          that.list[i].nums--;
        }
        that.type=5;
        that.isMaskFlag=true;
      }
    },
    close:function(){//关闭弹窗
      this.isMaskFlag=false;
    },
    addChance:function(){
      this.chance+=20;
      this.rate=parseInt((this.chance-98)/this.chance*100)+'%';

    }
  }
}
</script>


<style scoped>

</style>
