
<template>
  <div class="index">
    <div id="index_pc_bj">
      <Form ref="formLogin" :model="formLogin" :rules="ruleLogin">
        <div class="wrap_conter">
          <ul style="list-style: none;box-shadow:10px 10px 20px rgba(0,0,0,.5);">
            <li style="border-bottom: 1px solid #e9eaec;">

              <div class="content">
                <img src="../../static/images/logo.png" style="width: 40px;height: 40px;" align="absmiddle" />
                <span style="float:right;font-size: 15px">欢迎登录</span>
              </div>

            </li>
            <li>
              <div class="name-password-error" v-if="this.$store.state.ifSign">用户名或密码错误</div>
              <dl>
                  <input v-model="formLogin.userName" type="text" placeholder="登录名" >

                  <input v-model="formLogin.password" type="password" placeholder="密码" >

                  <Button type="primary" @click="login('formLogin')" style="width: 250px">登录</Button>

                  <ul class="account-list">
                    <li>
                      <a onclick="javascript:alert('第三方登录功能尚未实现！')" href="https://github.com/login/oauth/authorize?client_id=bbb5cc2034eb62484c1c&state=github" style="{right: 26px;}">

                        <img class="icon" src="../../static/images/GitHub.svg" />
                      </a>
                    </li>

                    <li>
                      <a onclick="javascript:alert('第三方登录功能尚未实现！')" href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101512648&redirect_uri=http://localhost:8088/qq&state=qq" style="{right: 26px;}">
                        <img class="icon" src="../../static/images/social-qq.svg" />
                      </a>
                    </li>
                    <li>
                      <a href="#/signUp" style="float: right;"><p style="color: black">注册新账户</p></a>
                    </li>
                  </ul>
              </dl>

            </li>

          </ul>
        </div>
      </Form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      code: null,
      formLogin: {
        userName: null,
        password: null
      },
      ruleLogin: {
        userName: [
          { required: true, message: "请填写用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请填写密码", trigger: "blur" }]
      }
    };
  },
  mounted() {
    var ui =document.getElementById("MyHtmlBottom");
    ui.style.visibility="hidden";
  },
  methods: {
    login(formLogin) {
      self=this
      this.axios.post(//"http://localhost:8088/user/validate"
        "http://www.buensports.com:8088/user/validate",
        {
        email: this.formLogin.userName,
        password: this.formLogin.password
      })
        .then(function (response) {
          console.log(response);
          response=response.data.toString();
          console.log(response);
          if(response=="不存在此账号")
          {
            alert("不存在此账号");
          }
          else if(response=="false")
          {
            alert("密码错误");
          }
          else if(response=="true")
          {
            alert("登陆成功！");
            localStorage.setItem("userName", self.formLogin.userName);
            localStorage.setItem("password", self.formLogin.password);
            self.$router.push({name:"home", params:{userName:self.formLogin.userName, password:self.formLogin.password}})
          }
          else
          {
            alert("未知错误");
          }

        })
        .catch(function (error) {
          console.log(error);
        });
     },
    register() {
      this.$router.push({ path: "/register" });
    }
  }
};
</script>

<style scoped>
.index {
  align-items: center;
  display: flex;
  width: 100%;
  min-width: 300px;
  min-height: 300px;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  text-align: center;
  background: #F8F8F8;

}

#index_pc_bj {
  width: 100%;
  height: auto;
  background-size: cover;
  overflow: hidden;
  background-position: center center;
  text-align: center;
}

.wrap_conter ul {
  position: relative;
  width: 300px;
  border-radius: 5px;
  background: #fff;
  margin: 0 auto;
}
.wrap_conter li {
  text-align: center;
  color: #fff;
  font-size: 12px;
  line-height: 30px;
  padding: 0 0px 0px 0px;
  width: 100%;
}
.content {
  color: #1c2438;
  line-height: 40px;
  display: block;
  text-align: left;
  padding: 5px 0 0 0;
  margin: 0 80px 0 20px;
}

.wrap_conter li dl {
  width: 100%;
  margin-top: 20px;
}
.name-password-error {
  padding-bottom: 2px;
  text-align: left;
  line-height: 1;
  color: #ed3f14;
}
.ivu-form-item-content .account-list {
  width: auto;
  padding-top: 10px;
}
.account-list li {
  width: auto;
  display: inline-block;
}

.account-list .icon {
  width: 35px;
  height: 40px;
}
</style>
