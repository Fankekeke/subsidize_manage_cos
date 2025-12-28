<template>
  <a-card :bordered="false" class="card-area">
    <div style="width: 100%">
      <a-col :span="22" v-if="newsList.length > 0">
        <a-alert
          banner
          :message="newsContent"
          type="info"
        />
      </a-col>
      <a-col :span="2">
        <a-button type="primary" style="margin-top: 2px;margin-left: 10px" @click="newsNext">下一页</a-button>
      </a-col>
    </div>
    <br/>
    <br/>
    <a-row :gutter="30" v-if="userInfo != null">
      <a-col :span="12" style="margin-top: 65px;text-align: center">
        <img alt="example" style="width: 500px;height: 500px;" src="/static/img/Search_SVG.png"/>
      </a-col>
      <a-col :span="12" style="margin-top: 80px">
        <p style="font-size: 30px;font-family: SimHei;font-weight: 500">欢迎使用大学生资助系统</p>
        <a-card :bordered="false">
  <span slot="title">
    <a-icon type="user" style="margin-right: 10px" />
    <span>学生信息</span>
  </span>
          <div style="display: flex; align-items: flex-start;">
            <!-- 头像区域 -->
            <div style="margin-right: 20px;">
              <a-avatar
                :src="'http://127.0.0.1:9527/imagesWeb/' + userInfo.images.split(',')[0]"
                shape="circle"
                style="width: 120px; height: 120px; border: 3px solid #f0f0f0;"
              />
            </div>

            <!-- 信息区域 -->
            <div style="flex: 1;">
              <!-- 姓名和学号 -->
              <div style="margin-bottom: 15px;">
                <h3 style="margin: 0; font-size: 22px; font-weight: bold; color: #1890ff; margin-bottom: 5px;">
                  {{ userInfo.name }}
                </h3>
                <span style="font-size: 14px; color: #666;">学号：{{ userInfo.code || '- -' }}</span>
              </div>

              <!-- 详细信息网格 -->
              <div style="display: grid; grid-template-columns: repeat(2, 1fr); gap: 12px;">
                <div class="info-item">
                  <span class="info-label">电话：</span>
                  <span class="info-value">{{ userInfo.phone == null ? '- -' : userInfo.phone }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">邮箱：</span>
                  <span class="info-value">{{ userInfo.email == null ? '- -' : userInfo.email }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">性别：</span>
                  <span class="info-value">{{ userInfo.sex == '1' ? '男' : userInfo.sex == '0' ? '女' : '- -' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">班级：</span>
                  <span class="info-value">{{ userInfo.className || '- -' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">专业：</span>
                  <span class="info-value">{{ userInfo.majorName || '- -' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">辅导员：</span>
                  <span class="info-value">{{ userInfo.staffName || '- -' }}</span>
                </div>
                <div class="info-item">
                  <span class="info-label">院系：</span>
                  <span class="info-value">{{ userInfo.tieName || '- -' }}</span>
                </div>
              </div>
            </div>
          </div>
        </a-card>
      </a-col>
      <a-col :span="24">
        <div style="background:#ECECEC; padding:30px;margin-top: 30px">
          <a-card :bordered="false">
            <a-spin :spinning="dataLoading">
              <a-calendar>
                <ul slot="dateCellRender" slot-scope="value" class="events">
                  <li v-for="item in getListData(value)" :key="item.content">
                    <a-badge :status="item.type" :text="item.content" />
                  </li>
                </ul>
              </a-calendar>
            </a-spin>
          </a-card>
        </div>
      </a-col>
    </a-row>
  </a-card>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')

const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'Work',
  data () {
    return {
      form: this.$form.createForm(this),
      formItemLayout,
      visible: false,
      statusList: [],
      vehicleList: [],
      newsContent: '',
      newsPage: 0,
      loading: false,
      userInfo: null,
      memberInfo: null,
      spaceInfo: null,
      newsList: [],
      courseInfo: [],
      dataLoading: false,
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    })
  },
  mounted () {
    this.selectBulletinDetail()
  },
  methods: {
    isDuringDate (beginDateStr, endDateStr, curDataStr) {
      let curDate = new Date(curDataStr)
      let beginDate = new Date(beginDateStr)
      let endDate = new Date(endDateStr)
      if (curDate >= beginDate && curDate <= endDate) {
        return true
      }
      return false
    },
    getListData (value) {
      let listData = []
      this.courseInfo.forEach(item => {
        if ((moment(value).format('YYYY-MM-DD')) === (moment(item.courseDate).format('YYYY-MM-DD'))) {
          listData.push({type: 'none', content: item.courseName})
        }
      })
      return listData || []
    },
    newsNext () {
      if (this.newsPage + 1 === this.newsList.length) {
        this.newsPage = 0
      } else {
        this.newsPage += 1
      }
      this.newsContent = `《${this.newsList[this.newsPage].title}》 ${this.newsList[this.newsPage].content.slice(0, 65)}`
    },
    handleCancel (e) {
      console.log('Clicked cancel button')
      this.visible = false
    },
    selectBulletinDetail () {
      this.dataLoading = true
      this.$get(`/cos/student-info/selectBulletinDetail/${this.currentUser.userId}`).then((r) => {
        this.userInfo = r.data.user
        this.newsList = r.data.bulletin
        if (this.newsList.length !== 0) {
          this.newsContent = `《${this.newsList[0].title}》 ${this.newsList[0].content.slice(0, 65)}`
        }
        this.courseInfo = r.data.order
        this.dataLoading = false
      })
    }
  }
}
</script>

<style scoped>
.info-item {
  display: flex;
  padding: 6px 0;
  border-bottom: 1px solid #f0f0f0;
}

.info-label {
  font-weight: bold;
  color: #555;
  min-width: 80px;
  flex-shrink: 0;
}

.info-value {
  color: #333;
  flex: 1;
  word-break: break-word;
}

>>> .ant-card-head-title {
  font-size: 15px;
  font-weight: bold;
  font-family: SimHei;
}

>>> .ant-card-body {
  padding: 20px
}
>>> .ant-card-meta-title {
  font-size: 13px;
  font-family: SimHei;
}
>>> .ant-card-meta-description {
  font-size: 12px;
  font-family: SimHei;
}
>>> .ant-divider-with-text-left {
  margin: 0;
}

>>> .ant-card-head-title {
  font-size: 13px;
  font-family: SimHei;
}
>>> .ant-card-extra {
  font-size: 13px;
  font-family: SimHei;
}
.ant-carousel >>> .slick-slide {
  text-align: center;
  height: 250px;
  line-height: 250px;
  overflow: hidden;
}

</style>
