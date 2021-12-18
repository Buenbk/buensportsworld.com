<template>
  <div id="mywork">
    <div class="page-header-main">
      <div class="width-80 margin-auto margin-top-2 flex-direction-column flex-justify-center flex-items-center">
        <div class="article">
          <div class="ql-container ql-snow">
            <div class="ql-editor">
              <div class="title line-height-40 font-size-22">{{article.title}}</div>
              <dl class="list-user-other">

                <dd>{{article.createTime}}</dd>

                <p style="color: #0971bb;width: 100%;height: 100%;">本文章来自腾讯体育</p>

              </dl>
              <div class="box-flex width-100 margin-auto margin-top-1 margin-bottom-1 border-top border-color-bfbfbf"></div>
              <img :src="article.myimg">
              <div class="content" v-html="article.content"></div>
            </div>
          </div>
        </div>

        <div class="comment-append-section" id="commentAppendArea">

          <Button class="newButtonStyle" style="margin-top: 10px; background: #ff9400; font-size: larger" type="primary" @click="light()">点亮在看 💡</Button>
        </div>


      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
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
      },
      comments: [],
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
    this.getNews(this.$route.params.id);

    console.log("this.article.createTime", this.article.createTime);
    console.log("this.article.content", this.article.content);

  },
  methods: {
    getNews(e) {
      this.axios({
        method: "get",
        //url: "http://localhost:8088/news/details/" + e
        url: "http://www.buensports.com:8088/news/details/" + e
      }).then(
        function (response) {

          this.articleSet(response.data);

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
      this.article.title = e.title;
      this.article.content = e.content;
      this.article.clickRate = e.clickRate;
      this.article.createTime = e.createdOn.slice(0, 10);
    },

    light()
    {
      alert("亮起来！");
    }


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

