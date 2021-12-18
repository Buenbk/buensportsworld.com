<template>
  <div id="work">

    <div id="head">
      <p v-if="fieldId==1" style="padding-left: 140px;font-size: 60px;color: #FF6600;border: 1px solid #EE6600">湖人专区</p>
      <p v-if="fieldId==2" style="padding-left: 140px;font-size: 60px;color: #FF6600;border: 1px solid #EE6600">火箭专区</p>
      <p v-if="fieldId==3" style="padding-left: 140px;font-size: 60px;color: #FF6600;border: 1px solid #EE6600">勇士专区</p>
    </div>

      <router-link :to="('/otherPersonHome/'+adm1)" v-if="fieldId==1">
        <p style="color: #BB8F09;width: 100%;height: 100%;text-align: center">
        🏀湖人专区管理员
        </p>
      </router-link>

    <router-link :to="('/otherPersonHome/'+adm2)" v-if="fieldId==2">
      <p style="color: #BB8F09;width: 100%;height: 100%;text-align: center">
        🏀火箭专区管理员
      </p>
    </router-link>

    <router-link :to="('/otherPersonHome/'+adm3)" v-if="fieldId==3">
      <p style="color: #BB8F09;width: 100%;height: 100%;text-align: center">
        🏀勇士专区管理员
      </p>
    </router-link>



    <div class="page-header-main">

      <div class="box-flex width-80 margin-auto margin-top-2 flex-direction-column flex-justify-center flex-items-center" >

        <div style="width: 100%;margin: 0 0 20px 0">
          <ol>
            <li v-for="(item,index) in articleList" :key="index" class="card-body">

              <div class="title">
                <router-link :to="('/article/detail/'+item.id)">
                  <span class="tirtleFont lineThrou ">{{item.draft}}</span>
                </router-link>
              </div>

              <div class="info">
                <!--                <p>{{item.draft}}</p>-->
              </div>

              <dl class="list-user-other">
                <dt>
                  <router-link :to="('/otherPersonHome/'+item.user.id)">
                    <img :src="item.user.userImg" style="width: 25px;height: 25px;border-radius: 100%;">
                  </router-link>
                </dt>

                <dd>
                  <router-link :to="('/otherPersonHome/'+item.user.id)">
                    {{item.user.username}}
                  </router-link>
                </dd>

                <div class="interval"></div>
                <dd>{{item.createdOn}}</dd>

                <div class="right-info">
                  <dd>
                    <router-link :to="('/article/details/'+item.id)">

                    </router-link>
                  </dd>
                  <div class="interval"></div>

                  <dd>
                    <router-link :to="('/article/details/'+item.id)">
                      <span class="text">评论数</span>
                      <span class="num">{{item.comments.length}}</span>
                    </router-link>
                  </dd>

                </div>

              </dl>
              <br><div class="myLine"></div>

            </li>

          </ol>

          <div style="margin-top: 20px">

          </div>
        </div>

      </div>

    </div>

  </div>
</template>


<script>
export default {
  data() {
    return {
      fieldId:null,
      userName:null,
      password:null,
      loginFlag:false,
      user:null,
      adm1:localStorage.getItem('adm1'),
      adm2:localStorage.getItem('adm2'),
      adm3:localStorage.getItem('adm3'),

      searchContent:null,
      articleList: [],
      total: 0,
      pageInfo: {
        page: 0,
        pageSize: 20
      }
    };
  },
  mounted() {
    var ui =document.getElementById("MyHtmlBottom");
    ui.style.visibility="visible";
    var node = document.getElementById("HomeTopDiv");
    var node2 = document.getElementById("blue");
    var node3 = document.getElementById("game");
    var node4 = document.getElementById("MyHtmlBottom");
    var nbalogoNode = document.getElementById("nbalogo");

    node.style.visibility="visible";
    node2.style.visibility="visible";
    node3.style.visibility="visible";
    node4.style.visibility="visible";
    nbalogoNode.style.visibility="visible";


    this.fieldId = this.$route.params.id;


    this.getArticle({
      pageInfo: this.pageInfo
    });
    this.renderUserInfo();

  },
  watch: {
    $route: ["search"]
  },
  methods: {
    getDate(date){
      return date.slice(0,10);
    },
    search(){
      this.searchContent = this.$route.query.searchValue;
      this.getArticle();
    },
    getArticle() {
      var me=this;
      this.axios({
        method: "get",
        //url: "http://localhost:8088/article/details/fieldid/"+me.fieldId,
        url: "http://www.buensports.com:8088/article/details/fieldid/"+me.fieldId,
        params: {
        }
      })
        .then(
          function(response) {
            this.articleList = response.data;
            this.articleList.forEach(
              function (item)
              {
                item.createdOn=item.createdOn.slice(0, 10);
              }
            )
            this.total = response.data.length;
            if (this.total==0)
            {
              alert("本专区还没有文章哦！")
            }

          }.bind(this)
        )
        .catch(function(error) {

        });
    },


    quitMyLogin(){
      alert("已退出")
      localStorage.clear();

    },

    renderUserInfo(){
      console.log("rendering...")
      //var node = document.getElementById("LoginToUser").remove();
      var node = document.getElementById("LoginToUser");
      var node2 = document.getElementsByClassName("headright");

      var newNode = document.createElement("span");
      var newNodeQuit = document.createElement("li");
      newNodeQuit.setAttribute("class", "vip");
      newNodeQuit.setAttribute("style", "width: 136px;height: 36px;margin-top: 20px;");
      //var userURL = this.userUrlPre+this.user.id;
      var userURL = "#/personHome";
      newNode.innerHTML="<a href='"+userURL+"' ><img class='basicInfo-left' src='"+localStorage.getItem("userImg")+"' width='50' height='50'></img></a>";
      newNodeQuit.innerHTML="<a href='javascript: quitMyLogin()' style='color: #BB8F09;width: 100%;height: 100%;'>退出</a>";

      node.parentNode.replaceChild(newNode, node);
      node2[0].appendChild(newNodeQuit);
    }

  }
};
</script>




<style scoped>
.card-body {
  padding: 10px;
}

.chat span{
  margin-left: 2px;
}
.title {
  margin-bottom: 8px;
}
.title span{
  color: #2d64b3;
}
.title:hover{
  background: rgb(230,230,230);
}
.info {
  margin-bottom: 5px;
}
.info p{
  width:100%;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}
.list-user-other {
  height: 24px;
  line-height: 24px;
}
.list-user-other dt{
  float: left;
  margin: 0 6px 0 0;
}
.list-user-other dd{
  float: left;
  font-size: 14px;
  color: #8a8a8a;
  line-height: 24px;
}
.list-user-other .right-info {
  line-height: 24px;
  float: right;
}
.list-user-other .right-info .text {
  margin-right: 4px;
  color: #8a8a8a;
}
.list-user-other .right-info .num {
  color: #3399ea;
}
.interval {
  float: left;
  width: 1px;
  height: 12px;
  border-radius: 50%;
  background-color: #e0e0e0;
  margin-top: 6px;
  margin-left: 8px;
  margin-right: 8px;
}
</style>



