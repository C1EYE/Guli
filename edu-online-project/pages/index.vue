<template>
  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <div class="swiper-slide" style="background: #040b1b">
          <a target="_blank" href="/">
            <img src="~/assets/photo/banner/1.jpg" alt="首页banner" />
          </a>
        </div>
        <div class="swiper-slide" style="background: #040b1b">
          <a target="_blank" href="/">
            <img src="~/assets/photo/banner/2.jpg" alt="首页banner" />
          </a>
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
    </div>
    <!-- 幻灯片 结束 -->

    <div id="aCoursesList">
      <!-- 网校课程 开始 -->
      <section v-if="data.total == 0" class="no-data-wrap">
        <em class="icon30 no-data-ico">&nbsp;</em>
        <span class="c-666 fsize14 ml10 vam"
          >没有相关数据，小编正在努力整理中...</span
        >
      </section>
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门课程</span>
            </h2>
          </header>
          <div>
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
            <section class="tac pt20">
              <a href="course" title="全部课程" class="comm-btn c-btn-2"
                >全部课程</a
              >
            </section>
          </div>
        </section>
      </div>
      <!-- /网校课程 结束 -->
      <!-- 网校名师 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">名师大咖</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="record in teacherList.records" :key="record.id">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a
                        :href="'teacher/' + record.id"
                        :title="record.name"
                        target="_blank"
                      >
                        <img v-if="record.avatar != ''" :src="record.avatar" />
                        <img
                          v-else
                          src="~/assets/photo/teacher/1442297919077.jpg"
                        />
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a
                        :href="'teacher/' + record.id"
                        :title="record.name"
                        target="_blank"
                        class="fsize18 c-666"
                        >{{ record.name }}</a
                      >
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{ record.career }}</span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <p class="c-999 f-fA">{{ record.intro }}</p>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="teacher" title="全部讲师" class="comm-btn c-btn-2"
                >全部讲师</a
              >
            </section>
          </div>
        </section>
      </div>
      <!-- /网校名师 结束 -->
    </div>
  </div>
</template>

<script>
import course from "@/api/course";
import teacher from "@/api/teacher";
export default {
  data() {
    return {
      swiperOption: {
        //swiper3
        autoplay: 3000,
        speed: 1000,
      },
      teacherList: {},
    };
  },

  asyncData({ params, error }) {
    return course.getCourseListPage(1, 8).then((response) => {
      console.log(response.data.data);
      return { data: response.data.data.map };
    });
  },
  mounted(){
    teacher.getTeacherListPage(1, 4).then((response) => {
        console.log(response.data.data);
        this.teacherList = response.data.data.map;
      });
  },

  methods: {
    gotoPage(page) {
      course.getCourseListPage(page, 8).then((response) => {
        this.data = response.data.data.map;
      });
    },
  },
};
</script>