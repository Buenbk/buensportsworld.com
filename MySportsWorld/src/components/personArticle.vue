<template>
  <div id="work">

    <div id="head">
      <p style="padding-left: 140px;font-size: 60px;color: #FF6600;border: 1px solid #EE6600">我的文章</p>
    </div>
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
      userName:null,
      password:null,
      loginFlag:false,
      user:null,

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
    if(localStorage.getItem("userName")==null)
    {
      alert("您还没有登录，请先登录！");
      this.$router.push({ path: "/article" });
      //location.reload()
    }
    this.getArticle({
      pageInfo: this.pageInfo
    });

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
      this.axios({
        method: "get",
        //url: "http://localhost:8088/article/details/userid/"+localStorage.getItem("userId"),
        url: "http://www.buensports.com:8088/article/details/userid/"+localStorage.getItem("userId"),
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
              alert("您还没有文章哦！")
            }

          }.bind(this)
        )
        .catch(function(error) {
          //alert(error);
        });
    },


    quitMyLogin(){
      alert("已退出")
      localStorage.clear();

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



