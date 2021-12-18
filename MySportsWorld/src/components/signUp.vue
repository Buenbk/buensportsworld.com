<template>
  <div id="form-main">
    <div id="form-div">
      <form class="form" id="form1">

        <p class="name">
          <input v-model="name" name="name" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="昵称" id="name" />
        </p>

        <p class="name">
          <input v-model="email" name="email" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="邮箱" id="email" />
        </p>

        <p class="name">
          <input v-model="password" type="password" name="password" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="密码" id="password" />
        </p>

        <p class="text">
          <textarea v-model="bio" name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="简介"></textarea>
        </p>

        <div class="submit">
          <input type="submit" value="注册" id="button-blue" @click="postPersonInfo()"/>
          <div class="ease"></div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "writeArticle",
  data() {
    return {
      name: null,
      email:null,
      password:null,
      bio: null,
      userImg:null,
      imgAddressPre: "../../static/images/",
      imgAddressAft: ".jfif",
    };
  },
  mounted() {
    var node = document.getElementById("HomeTopDiv");
    var node2 = document.getElementById("blue");
    var node3 = document.getElementById("game");

    var node4 = document.getElementById("MyHtmlBottom");

    //node.style.visibility="hidden";
    //node2.style.visibility="hidden";
    node3.style.visibility="hidden";
    node4.style.visibility="hidden";

  },
  methods: {
    postPersonInfo(){

      if(this.name == null || this.name == ''){
        //this.$Message.error('请输入评论');
        alert("请输入昵称");
        return;
      }
      if(this.email == null || this.email == ''){
        //this.$Message.error('请输入评论');
        alert("请输入邮箱");
        return;
      }
      if(this.password == null || this.password == ''){
        //this.$Message.error('请输入评论');
        alert("请输入密码");
        return;
      }
      if(this.bio == null || this.bio == ''){
        //this.$Message.error('请输入评论');
        alert("请输入简介");
        return;
      }


      this.postByAxios({
        "email":this.email,
        "username":this.name,
        "emailPassword":{"email":this.email,"password":this.password},
        "bio":this.bio,
        "userImg":"../../static/images/nba.jpg"
      });

    },

    postByAxios(e)
    {
      //alert(JSON.stringify(e));
      this.axios({
        method: 'post',
        //url: "http://localhost:8088/user/details",
        url: "http://www.buensports.com:8088/user/details",
        data: e
      }).then(function (response) {
        alert("注册成功");
      }.bind(this)).catch(function () {
        alert("注册失败");
      }.bind(this));
      this.$router.push({ path: "/article" });
      location.reload(true);
    }

  }
}
</script>

<style scoped>
/*@import url(https://fonts.googleapis.com/css?family=Montserrat:400,700);*/

html{    background:url(http://thekitemap.com/images/feedback-img.jpg) no-repeat;
  background-size: cover;
  height:100%;
}

#feedback-page{
  text-align:center;
}

#form-main{
  width:100%;
  float:left;
  padding-top:0px;
}

#form-div {
  background-color:rgba(72,72,72,0.4);
  padding-left:35px;
  padding-right:35px;
  padding-top:35px;
  padding-bottom:50px;
  width: 900px;
  float: left;
  left: 0%;
  position: absolute;
  margin-top:30px;
  margin-left: 190px;
  -moz-border-radius: 7px;
  -webkit-border-radius: 7px;
}

.feedback-input {
  color:#3c3c3c;
  font-family: Helvetica, Arial, sans-serif;
  font-weight:500;
  font-size: 18px;
  border-radius: 0;
  line-height: 22px;
  background-color: #fbfbfb;
  padding: 13px 13px 13px 54px;
  margin-bottom: 10px;
  width:100%;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  box-sizing: border-box;
  border: 3px solid rgba(0,0,0,0);
}

.feedback-input:focus{
  background: #fff;
  /*box-shadow: 0;*/
  border: 3px solid #3498db;
  color: #3498db;
  outline: none;
  padding: 13px 13px 13px 54px;
}

.focused{
  color:#30aed6;
  border:#30aed6 solid 3px;
}

/* Icons ---------------------------------- */
#name{
  background-image: url(../../static/images/篮球.jpeg);
  background-size: 30px 30px;
  background-position: 11px 8px;
  background-repeat: no-repeat;
}


#comment{
  background-image: url(../../static/images/篮球.jpeg);
  background-size: 30px 30px;
  background-position: 11px 8px;
  background-repeat: no-repeat;
}

textarea {
  width: 100%;
  height: 300px;
  line-height: 150%;
  resize:vertical;
}

input:hover, textarea:hover,
input:focus, textarea:focus {
  background-color:white;
}

#button-blue{
  font-family: 'Montserrat', Arial, Helvetica, sans-serif;
  float:left;
  width: 100%;
  border: #fbfbfb solid 4px;
  cursor:pointer;
  background-color: #3498db;
  color:white;
  font-size:24px;
  padding-top:22px;
  padding-bottom:22px;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  transition: all 0.3s;
  margin-top:-4px;
  font-weight:700;
}

#button-blue:hover{
  background-color: rgba(0,0,0,0);
  color: #0493bd;
}

.submit:hover {
  color: #3498db;
}

.ease {
  width: 0px;
  height: 74px;
  background-color: #fbfbfb;
  -webkit-transition: .3s ease;
  -moz-transition: .3s ease;
  -o-transition: .3s ease;
  -ms-transition: .3s ease;
  transition: .3s ease;
}

.submit:hover .ease{
  width:100%;
  background-color:white;
}

@media only screen and (max-width: 580px) {
  #form-div{
    left: 3%;
    margin-right: 3%;
    width: 88%;
    margin-left: 0;
    padding-left: 3%;
    padding-right: 3%;
  }
}

</style>

<style>

.form-radio-label {
  position: relative;
  cursor: pointer;
  padding-left: 1.5rem;
  text-align: left;
  color: #333;
  display: block;
  margin-bottom: 0.5rem;
}


.form-radio-label:hover i {
  color: #ff9400;
}


.form-radio-label span {
  display: block;
}


.form-radio-label input {
  width: auto;
  opacity: 0.0001;
  position: absolute;
  left: 0.25rem;
  top: 0.25rem;
  margin: 0;
  padding: 0;
}

.form-radio-button {
  position: absolute;
  left: 0;
  cursor: pointer;
  display: block;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  color: #ffffff;
}

.form-radio-button::before,
.form-radio-button::after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  margin: 0.25rem;
  width: 1rem;
  height: 1rem;
  transition: transform 0.28s ease, color 0.28s ease;
  border-radius: 50%;
  border: 0.125rem solid currentColor;
  will-change: transform, color;
}

.form-radio-button::after {
  transform: scale(0);
  background-color: #ffffff;
  border-color: #ffffff;
}

.form-radio-field:checked ~ .form-radio-button::after {
  transform: scale(0.5);
}

.form-radio-field:checked ~ .form-radio-button::before {
  color: #ffffff;
}



</style>
