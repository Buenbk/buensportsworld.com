import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'
import article from "../components/Article"
import news from "../components/News"
import articleDetail from "../components/article-detail"
import newsDetail from "../components/news-detail"
import login from "../components/login"
import nbaWorld from "../components/nbaWorld";
import personHome from "../components/personHome";
import otherPersonHome from "../components/otherPersonHome";
import writeArticle from "../components/writeArticle";
import personArticle from "../components/personArticle";
import fieldArticle from "../components/fieldArticle";
import personInfo from "../components/personInfo";
import signUp from "../components/signUp";
Vue.use(Router)



export default new Router({
  routes: [
    /*{
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },*/
    /*{
      path:"/",
      redirect:"/home"
    },*/
    {
      path: '/',
      name: 'home',
      component: article
    },
    {
      path: '/article',
      name: 'article',
      component: article
    },
    {
      path: '/news',
      name: 'news',
      component: news
    },
    {
      path: '/article/detail/:id',
      name: 'articleDetail',
      component: articleDetail
    },
    {
      path: '/news/detail/:id',
      name: 'newsDetail',
      component: newsDetail
    },
    {
      path: '/login',
      name: "login",
      component: login
    },
    {
      path: '/nbaWorld',
      name: "nbaWorld",
      component: nbaWorld
    },
    {
      path: "/personHome",
      name: "personHome",
      component: personHome
    },
    {
      path: "/otherPersonHome/:id",
      name: "otherPersonHome",
      component: otherPersonHome
    },
    {
      path: "/personArticle",
      name: "personArticle",
      component: personArticle
    },
    {
      path: "/fieldArticle/:id",
      name: "fieldArticle",
      component: fieldArticle
    },
    {
      path: "/writeArticle",
      name: "writeArticle",
      component: writeArticle
    },
    {
      path: "/qq",
      component: article,

    },
    {
      path: "/personInfo",
      name: "personInfo",
      component: personInfo
    },
    {
      path: "/signUp",
      name: "signUp",
      component: signUp
    }
  ]
})
