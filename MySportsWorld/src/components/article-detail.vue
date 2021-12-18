<template>
  <div id="mywork">
    <div class="page-header-main">
      <div class="width-80 margin-auto margin-top-2 flex-direction-column flex-justify-center flex-items-center">
        <div class="article">
          <div class="ql-container ql-snow">
            <div class="ql-editor">
              <div class="title line-height-40 font-size-22">{{article.title}}</div>
              <dl class="list-user-other">
                <dt>
                  <router-link :to="('/otherPersonHome/'+article.userid)">
                    <img :src="article.userHeadImg"
                         style="width: 25px;height: 25px;border-radius: 100%;">
                  </router-link>

                </dt>
                <dd>
                  <router-link :to="('/otherPersonHome/'+article.userid)">
                    {{article.userName}}
                  </router-link>
                </dd>
                <dd>{{article.createTime}}</dd>
                <dd @click="deleteArticle" id="deleteArticle"><a href=''><p>删除文章</p></a></dd>
                <a v-if="fieldId==1" href="#/fieldArticle/1" style="color: #BB8F09;width: 100%;height: 100%;">🏀湖人专区</a>
                <a v-if="fieldId==2" href="#/fieldArticle/2" style="color: #BB8F09;width: 100%;height: 100%;">🏀火箭专区</a>
                <a v-if="fieldId==3" href="#/fieldArticle/3" style="color: #BB8F09;width: 100%;height: 100%;">🏀勇士专区</a>
                <dd v-for="(tag, index) in tags" :key="tag.id">
                  <p style="color: #0971bb;width: 100%;height: 100%;">{{tag.name}}</p>
                </dd>
              </dl>
              <div class="box-flex width-100 margin-auto margin-top-1 margin-bottom-1 border-top border-color-bfbfbf"></div>
              <img :src="article.myimg">
              <div class="content" v-html="article.content"></div>
            </div>
          </div>
        </div>

        <div class="comment-append-section" id="commentAppendArea">
<!--          <Input v-model="commentAppend" @on-focus="focusInput" ref="commentInputDom" type="textarea" :rows="commentAppendInput" placeholder="添加评论"/>-->
          <p class="text">
            <textarea v-model="commentAppend" @on-focus="focusInput" ref="commentInputDom" name="text" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="添加评论"></textarea>
          </p>
          <Button class="newButtonStyle" style="margin-top: 10px" type="primary" @click="publishComment()">发表评论</Button>
        </div>

        <div class="comment-section">
           <div class="reply-count">
              {{total}}条回复
          </div>

          <ul class="reply-list">
            <li v-for="(comment, index) in comments" class="reply-item" :key="comment.id">

              <div class="user">
                <router-link :to="('/otherPersonHome/'+comment.userId)">
                  <img class="avatar" :src="comment.user.userImg" :title="comment.userId" style="width: 25px;height: 25px;border-radius: 100%;float: left"/>
                </router-link>

                <div class="title-info">
                  <router-link :to="('/otherPersonHome/'+comment.userId)">
                    <span class="user-name">{{comment.user.username}}</span>
                  </router-link>
                  <span># {{index + 1}} 楼</span>

                </div>

                <div class="title-info-other">
                </div>
              </div>
              <div class="reply-info">
                <div class="reply-content">
                  {{comment.body}}
                </div>
              </div>
              <dd :id="index" class="myComment" @click="deleteComment(index)"><a :href="urlArticle" style="font-size: 12px;color: #666;">删除评论</a></dd>

            </li>
          </ul>


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
      field:null,
      urlArticle:"#/article/detail/",
      articleId: null,
      article: {
        myimg:"",
        id: "",
        title: "",
        content: "",
        clickRate: "",
        commentCount: "",
        createTime: "",
        userid: "",
        userName: "",
        userHeadImg: "",
        githuburl: ""
      },
      comments: [],
      tags:[],
      total: 0,
      commentAppend: '',
      commentAppendInput:1,
      appendActive: false,
      articleComment: {
        articleid: null,
        articleOwnerId:null,
        parentid: null,
        comment: null,
        replierId: null,
        replierName: null
      }
    };
  },
  mounted() {
    this.articleId = this.$route.params.id;
    this.urlArticle = this.urlArticle+this.articleId.toString();
    this.getArticle(this.$route.params.id);

    setTimeout(() => {
      var nodes = document.getElementsByClassName("myComment");
      var i;
      for (i = 0; i < nodes.length; i++) {
        nodes[i].style.visibility="hidden";
      }
      console.log(nodes);
      console.log(nodes.length);
      for (i = 0; i < nodes.length; i++) {
        if(this.comments[i].userId.toString()==localStorage.getItem("userId").toString())
        {
          nodes[i].style.visibility="visible";
        }
        else
        {
          nodes[i].style.visibility="hidden";
        }
      }
    }, 200)

  },

  methods: {
    getArticle(e) {
      this.axios({
        method: "get",
        //url: "http://localhost:8088/article/details/" + e
        url: "http://www.buensports.com:8088/article/details/" + e
      }).then(
        function (response) {
          this.comments=response.data.comments;
          this.tags=response.data.tags;
          this.total=this.comments.length;
          this.articleSet(response.data);

          console.log(this.comments);
          console.log(this.total);
        }.bind(this)
      ).catch(
        function (error) {
          console.log(error);
        }.bind(this)
      );
    },
    articleSet(e) {
      this.article.myimg = e.imgAddress;
      this.article.id = e.id;
      this.article.title = e.draft;
      this.article.content = e.body;
      this.article.clickRate = e.clickRate;
      this.article.commentCount = e.comments.length;
      this.article.createTime = e.createdOn.slice(0, 10);
      this.article.userid = e.user.id;
      this.article.userName = e.user.username;
      this.article.userHeadImg = e.user.userImg;
      this.article.githuburl = e.githuburl;
      this.fieldId = e.fieldId;

      this.getField();

    },
    getField()
    {
      var me=this;
      this.axios({
        method: "get",
        //url: "http://localhost:8088/article/field/" + this.fieldId
        url: "http://www.buensports.com:8088/article/field/" + this.fieldId
      }).then(
        function (response) {
          //alert(response.data.admId);
          me.field = response.data;
          //alert(me.field.admId);
          var node = document.getElementById("deleteArticle");
          if(me.article.userid.toString()==localStorage.getItem("userId").toString()
            ||localStorage.getItem("userId").toString()==me.field.admId)
          {
            node.style.visibility="visible";
          }
          else {
            node.style.visibility="hidden";
          }
        }.bind(this)
      ).catch(
        function (error) {
          console.log(error);
        }.bind(this)
      );
    },
    focusInput(event){
      this.commentAppendInput = 3;
    },
    publishComment() {
      if (localStorage.getItem("userName")==null){
        //this.$Message.error('请登录');
        alert("请登录");
        return;
      }
      if(this.commentAppend == null || this.commentAppend == ''){
        //this.$Message.error('请输入评论');
        alert("请输入评论");
        return;
      }
      this.postComment({
        articleId:this.article.id,
        userId:localStorage.getItem("userId"),
        body:this.commentAppend
        });
    },
    postComment(e){
      this.axios({
        method: 'post',
        //url: 'http://localhost:8088/comment/details',
        url: 'http://www.buensports.com:8088/comment/details',
        data: e
      }).then(function (response) {
        alert("评论成功");
        location.reload(true);
      }.bind(this)).catch(function () {
        alert("评论失败");
      }.bind(this));
    },
    deleteArticle(){
      //alert(this.article.id);
      var id=this.article.id;
      this.axios({
        method: 'delete',
        //url: "http://localhost:8088/article/delete/"+id,
        url: "http://www.buensports.com:8088/article/delete/"+id,
      }).then(function (response) {
        alert("删除成功");
      }.bind(this)).catch(function (error) {
        alert("删除失败");
        alert("error: "+error);
      }.bind(this));
      this.$router.push({ path: "/article" });
    },

    deleteComment(index){
      var id=this.comments[index].id;
      this.axios({
        method: 'delete',
        //url: "http://localhost:8088/comment/delete/"+id,
        url: "http://www.buensports.com:8088/comment/delete/"+id,
      }).then(function (response) {
        alert("删除成功");
      }.bind(this)).catch(function (error) {
        alert("删除失败");
        alert("error: "+error);
      }.bind(this));
      this.$router.push({ path: "/article/detail/"+this.article.id });

      location.reload(true);
    },


  }

};
</script>


<style scoped>
.article {
  width: 100%;
  background: #fff;
  margin-bottom: 20px;
}

.title {
  color: black;
}

.list-user-other {
  margin: 10px 0px;
  height: 24px;
  line-height: 24px;
}

.list-user-other dt {
  float: left;
  margin: 0 6px 0 0;
}

.list-user-other dd {
  float: left;
  font-size: 14px;
  color: #8a8a8a;
  line-height: 24px;
  margin-right: 10px;
}

.list-user-other .text {
  margin-right: 4px;
  color: #8a8a8a;
}

.list-user-other .num {
  color: #3399ea;
}

.content {
  overflow: hidden;
}

.content img {
  max-width: 100%;
}

.comment-section {
  flex-direction: column;
  margin-top: 5px;
  background-color: #fff;
  overflow: hidden;
}

.comment-section ul {
  list-style: none;
}

.comment-section image {
  margin-right: 10px;
  border-radius: 6px;
}

.comment-section .avatar {
  margin-right: 6px;
  border-radius: 20px;
  width: 26px;
  height: 26px;
}

.comment-section .reply-count {
  padding: 12px 15px;
  background-color: #eee;
}

.comment-section .reply-list {
  display: flex;
  flex-direction: column;
}

.comment-section .reply-list .reply-item {
  display: flex;
  padding: 12px 15px;
  border-bottom: 1px solid #f0f0f0;
  flex-direction: column;
}

.comment-section .reply-list .reply-item .user {
  flex: 0 0 26px;
}

.comment-section .reply-list .reply-item .user .avatar {
  margin-right: 6px;
  border-radius: 20px;
  width: 26px;
  height: 26px;
  float: left;
}

.comment-section .reply-list .reply-item .user .title-info {
  font-size: 12px;
  float: left;
  margin-top: 4px;
}

.comment-section .reply-list .reply-item .user .title-info .user-name {
  margin: 0 4px;
  color: #666;
}

.comment-section .reply-list .reply-item .user .title-info .user-name:hover {
  color: #385f8a;
}

.comment-section .reply-list .reply-item .user .title-info-other {
  font-size: 12px;
  float: left;
  margin: 4px 0 0 20px;
}

.comment-section .reply-list .reply-item .user .title-info-other a {
  margin: 0 5px;
}

.comment-section .reply-list .reply-item .reply-child-list {
  margin-left: 32px;
  border-left: 4px solid #c5c5c5;
}

.comment-section .reply-list .reply-item .reply-child-list .title-info {
  font-size: 12px;
  float: left;
  margin-top: 4px;
}

.comment-section .reply-list .reply-item .reply-child-list .title-info .user-name {
  margin: 0 4px;
  color: #666;
}

.comment-section .reply-list .reply-item .reply-child-list .title-info .user-name:hover {
  color: #385f8a;
}

.comment-section .reply-list .reply-item .reply-child-list .title-info .avatar {
  margin-right: 6px;
  border-radius: 20px;
  width: 26px;
  height: 26px;
  float: left;
}

.comment-section .reply-list .reply-item .reply-child-list .title-info .title-info-right {
  float: left;
  margin-top: 4px;
}

.comment-section .reply-list .reply-item .reply-child-list .title-info .title-info-right .reply-time {
  padding: 0 20px;
}

.comment-section .reply-list .reply-hightlight {
  background-color: #f4fcf0;
}

.comment-section .reply-list .reply-info {
  display: flex;
  flex-direction: column;
}

.comment-section .reply-list .reply-info text {
  margin-left: 20px;
}

.comment-section .reply-list .up-info {
  flex-grow: 1;
  justify-content: flex-end;
  color: #808080;
}

.comment-section .reply-list .up-info image {
  width: 50px;
  height: 50px;
}

.comment-section .reply-list .up-info .up-count {
  margin-left: 0;
}

.comment-section .reply-content {
  padding: 10px 20px 0 0;
  font-size: 14px;
}

.comment-section .pagin {
  margin: 10px 15px;
}

</style>
