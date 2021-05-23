<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <a title="全部" href="#"  >全部</a>
                </li>
                <li  v-for="subject in tree" :key="subject.id" >
                  <a :title="subject.title" href="#" v-on:click.prevent="getSubjectListQW">{{ subject.title }}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li>
                <a title="关注度" href="#">关注度</a>
              </li>
              <li>
                <a title="最新" href="#">最新</a>
              </li>
              <li class="current bg-orange">
                <a title="价格" href="#"
                  >价格&nbsp;
                  <span>↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="data.total == 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关数据，小编正在努力整理中...</span
            >
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list">
            <ul class="of" id="bna">
              <li v-for="record in data.records" :key="record.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img
                      :src="record.cover"
                      class="img-responsive"
                      :alt="record.title"
                    />
                    <div class="cc-mask">
                      <a
                        :href="'/course/' + record.id"
                        title="开始学习"
                        class="comm-btn c-btn-1"
                        >开始学习</a
                      >
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a
                      :href="'/course/' + record.id"
                      :title="record.title"
                      class="course-title fsize18 c-333"
                      >{{ record.title }}</a
                    >
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">{{
                        record.price == 0 ? "免费" : "付费"
                      }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ record.viewCount }}人浏览</i>
                      |
                      <i class="c-999 f-fA">{{ record.buyCount }}人购买</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div class="paging">
          <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
          <a
            :class="{ undisable: !data.hasPrevious }"
            href="#"
            title="首页"
            @click.prevent="gotoPage(1)"
            >首</a
          >
          <a
            :class="{ undisable: !data.hasPrevious }"
            href="#"
            title="前一页"
            @click.prevent="gotoPage(data.current - 1)"
            >&lt;</a
          >
          <a
            v-for="page in data.pageCount"
            :key="page"
            :class="{
              current: data.current == page,
              undisable: data.current == page,
            }"
            :title="'第' + page + '页'"
            href="#"
            @click.prevent="gotoPage(page)"
            >{{ page }}</a
          >
          <a
            :class="{ undisable: !data.hasNext }"
            href="#"
            title="后一页"
            @click.prevent="gotoPage(data.current + 1)"
            >&gt;</a
          >
          <a
            :class="{ undisable: !data.hasNext }"
            href="#"
            title="末页"
            @click.prevent="gotoPage(data.pageCount)"
            >末</a
          >
          <div class="clear" />
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import course from "@/api/course";
export default {
  data() {
    return {
      queryWarpper: {
        subjectId: "",
        keyWord: "",
        //1表示最新
        new: 0,
        //1表示升序排列
        priceOrder: 0,
        //1表示关注度升序
        hot: 0,
      },
      tree: [{ title: "ALG", id: 1 }],
    };
  },
  asyncData({ params, error }) {
    return course.getCourseListPage(1, 8).then((response) => {
      console.log(response.data.data);
      return { data: response.data.data.map };
    });
  },

  created() {
    this.getSubjectList();
  },

  methods: {
    gotoPage(page) {
      course.getCourseListPage(page, 8).then((response) => {
        this.data = response.data.data.map;
      });
    },
    getSubjectList() {
      course.getSubjectList().then((response) => {
        this.tree = response.data.data.node;
      });
      console.log(this.tree);
    },
    getSubjectListQW() {
      course.getCourseListPageQueryWrapper(this.queryWarpper).then((response) => {
        console.log(response.data.data)
        alert("CAO!")
        this.data = response.data.data.map
        this.tree = this.data.records;
      });
      console.log(this.tree);
    },
  },
};
</script>