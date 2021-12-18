<template>
  <div id="work">

    <div class="loader loader--style7" title="6" v-if="isLoading">
      <p style="font-size: 20px;color: #FF6600">正在爬取腾讯体育网的新闻资讯</p>
      <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
           width="24px" height="30px" viewBox="0 0 24 30" style="enable-background:new 0 0 50 50;" xml:space="preserve">
    <rect x="0" y="0" width="4" height="20" fill="#333">
      <animate attributeName="opacity" attributeType="XML"
               values="1; .2; 1"
               begin="0s" dur="0.6s" repeatCount="indefinite" />
    </rect>
        <rect x="7" y="0" width="4" height="20" fill="#333">
      <animate attributeName="opacity" attributeType="XML"
               values="1; .2; 1"
               begin="0.2s" dur="0.6s" repeatCount="indefinite" />
    </rect>
        <rect x="14" y="0" width="4" height="20" fill="#333">
      <animate attributeName="opacity" attributeType="XML"
               values="1; .2; 1"
               begin="0.4s" dur="0.6s" repeatCount="indefinite" />
    </rect>
    </svg>
    </div>

    <div class="page-header-main">

      <div class="box-flex width-80 margin-auto margin-top-2 flex-direction-column flex-justify-center flex-items-center" >

        <div style="width: 100%;margin: 0 0 20px 0">
          <ol>
            <li v-for="(item,index) in articleList" :key="index" class="card-body">

              <div class="title">
                <router-link :to="('/news/detail/'+item.id)">
                  <span class="tirtleFont lineThrou ">{{item.title}}</span>
                </router-link>
              </div>

              <div class="info">

              </div>

              <dl class="list-user-other">

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


                    </router-link>
                  </dd>

                </div>

              </dl>
              <br><div class="myLine2"></div>

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
      },
      isLoading:true,
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

    this.getArticle();

    if (localStorage.getItem("userName")!=null)
    {

      this.userGet();

    }

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
        //url: "http://localhost:8088/news/getAllNews",
        url: "http://www.buensports.com:8088/news/getAllNews",
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

            this.isLoading = false;

          }.bind(this)
        )
        .catch(function(error) {
          alert(error);
        });
    },



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



