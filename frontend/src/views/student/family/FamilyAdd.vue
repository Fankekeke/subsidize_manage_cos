<template>
  <a-modal v-model="show" title="新增家庭成员" @cancel="onClose" :width="600">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='姓名' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入姓名!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='出生日期' v-bind="formItemLayout">
            <a-date-picker
              style="width: 100%"
              v-decorator="['birthday', { rules: [{ required: true, message: '请选择出生日期!' }] }]"
              placeholder="请选择出生日期"
              format="YYYY-MM-DD"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='与本人关系' v-bind="formItemLayout">
            <a-input v-decorator="[
            'relation',
            { rules: [{ required: true, message: '请输入与本人关系!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='工作单位' v-bind="formItemLayout">
            <a-input v-decorator="[
            'workUnit',
            { rules: [] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='月收入' v-bind="formItemLayout">
            <a-input-number
              style="width: 100%"
              v-decorator="['income', { rules: [] }]"
              placeholder="请输入月收入"
              :min="0"
              :step="0.01"
              :precision="2"
            />
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'dishesAdd',
  props: {
    moduleAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.moduleAddVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      staffList: [],
      tieList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  mounted () {
  },
  methods: {
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        images.push(image.response)
      })
      this.form.validateFields((err, values) => {
        if (!err) {
          if (values.birthday) {
            values.birthday = moment(values.birthday).format('YYYY-MM-DD')
          }
          values.userId = this.currentUser.userId
          this.loading = true
          this.$post('/cos/family-members', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
