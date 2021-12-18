<template>

  <div class="side_wrapper">
    <section class="about-dev">
      <header class="profile-card_header">
        <div class="profile-card_header-container">
          <div class="profile-card_header-imgbox">
            <img :src="userImg" alt="Mewy Pawpins" />
          </div>
        </div>
      </header>
      <div class="profile-card_about">
        <h2>{{userName}}</h2>
        <a v-if="adm1==userId" href="#/fieldArticle/1" style="color: #BB8F09;width: 100%;height: 100%;">🏀湖人专区管理员</a>
        <a v-if="adm2==userId" href="#/fieldArticle/2" style="color: #BB8F09;width: 100%;height: 100%;">🏀火箭专区管理员</a>
        <a v-if="adm3==userId" href="#/fieldArticle/3" style="color: #BB8F09;width: 100%;height: 100%;">🏀勇士专区管理员</a>
        <p>{{bio}}</p>
        <footer class="profile-card_footer">
          <div class="social-row">

          </div>
          <p><a class="back-to-profile" href="#/article">回到主页</a></p>
        </footer>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "personHome",
  data() {
    return {
      //userUrlPre: "http://localhost:8088/user/details/",
      userUrlPre: "http://www.buensports.com:8088/user/details/",
      userId: this.$route.params.id,
      userName:null,
      userImg:null,
      bio:null,
      adm1:localStorage.getItem('adm1'),
      adm2:localStorage.getItem('adm2'),
      adm3:localStorage.getItem('adm3'),
    };
  },
  mounted() {
    var node = document.getElementById("HomeTopDiv");
    var node2 = document.getElementById("blue");
    var node3 = document.getElementById("game");
    var nbalogoNode = document.getElementById("nbalogo");

    nbalogoNode.style.visibility="hidden";
    node.style.visibility="hidden";
    //node2.style.visibility="hidden";
    node3.style.visibility="hidden";

    this.getUser(this.userUrlPre, this.userId);

    console.log("userImg:",this.userImg);
    console.log("userName:",this.userName);
  },
  methods:{
    getUser(urlPre, id)
    {
      var self=this;
      this.axios({
        method: "get",
        url: urlPre+id
      })
        .then(
          function(response) {
            if (response.data != null && response.data != "") {
              self.userName=response.data.username;
              self.userImg=response.data.userImg;
              self.bio=response.data.bio;

            } else {
              console.log("没有用户信息");
              return Promise.resolve(0);
            }
          }.bind(this)
        )
        .then(function(response) {
        })
        .catch(
          function(error) {
            console.log(error);
          }.bind(this)
        );
    }
  }
}
</script>

<style src="../styles/personHome.css" scoped>

</style>

