<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 讲师列表 开始 -->
    <section class="container">
      <header class="comm-title all-teacher-title">
        <h2 class="fl tac">
          <span class="c-333">全部讲师</span>
        </h2>
        <section class="c-tab-title">
          <a id="subjectAll" title="全部" href="#">全部</a>
        </section>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section v-if="data.total == 0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关数据，小编正在努力整理中...</span
            >
          </section>
          <!-- /无数据提示 结束-->
          <article class="i-teacher-list">
            <ul class="of">
              <li v-for="record in data.records" :key="record.id">
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
                    <span class="fsize14 c-999">{{ record.intro }}</span>
                  </div>
                  <div class="mt15 i-q-txt">
                    <p class="c-999 f-fA">{{ record.career }}</p>
                  </div>
                </section>
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
            @click.prevent="gotoPage(data.pages)"
            >末</a
          >
          <div class="clear" />
        </div>
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /讲师列表 结束 -->
  </div>
</template>
<script>
import teacher from "@/api/teacher";
export default {
  methods: {
    gotoPage(page) {
      teacher.getTeacherListPage(page, 8).then((response) => {
        this.data = response.data.data.map;
      });
    },
  },
  asyncData({ params, error }) {
    return teacher.getTeacherListPage(1, 8).then((response) => {
      console.log(response.data.data);
      return { data: response.data.data.map };
    });
  },
};
</script>