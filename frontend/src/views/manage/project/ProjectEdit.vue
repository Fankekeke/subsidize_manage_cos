<template>
  <a-modal v-model="show" title="修改资助项目" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='资助项目名称' v-bind="formItemLayout">
            <a-input v-decorator="[
      'projectName',
      { rules: [{ required: true, message: '请输入资助项目名称!' }] }
      ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='项目类型' v-bind="formItemLayout">
            <a-select v-decorator="[
      'projectType',
      { rules: [{ required: true, message: '请选择项目类型!' }] }
      ]">
              <a-select-option value="奖学金">奖学金</a-select-option>
              <a-select-option value="助学金">助学金</a-select-option>
              <a-select-option value="勤工助学">勤工助学</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='发布者' v-bind="formItemLayout">
            <a-input v-decorator="[
      'publisherUser',
      { rules: [{ required: true, message: '请输入发布者!' }] }
      ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='申请开始日期' v-bind="formItemLayout">
            <a-date-picker
              style="width: 100%"
              v-decorator="[
        'startDate',
        { rules: [{ required: true, message: '请选择申请开始日期!' }] }
        ]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='申请截止日期' v-bind="formItemLayout">
            <a-date-picker
              style="width: 100%"
              v-decorator="[
        'endDate',
        { rules: [{ required: true, message: '请选择申请截止日期!' }] }
        ]"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='面向年级范围' v-bind="formItemLayout">
            <a-input v-decorator="[
      'targetGrade',
      { rules: [{ required: true, message: '请输入面向年级范围!' }] }
      ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='单人资助金额' v-bind="formItemLayout">
            <a-input-number
              style="width: 100%"
              v-decorator="[
        'amountPerPerson',
        { rules: [{ required: true, message: '请输入单人资助金额!' }] }
        ]"
              :min="0"
              :step="0.01"
              :formatter="value => `¥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
              :parser="value => value.replace(/¥\s?|(,*)/g, '')"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='总名额' v-bind="formItemLayout">
            <a-input-number
              style="width: 100%"
              v-decorator="[
        'quota',
        { rules: [{ required: true, message: '请输入总名额!' }] }
        ]"
              :min="1"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='项目状态' v-bind="formItemLayout">
            <a-select v-decorator="[
      'status',
      { rules: [{ required: true, message: '请选择项目状态!' }] }
      ]">
              <a-select-option value="发布中">发布中</a-select-option>
              <a-select-option value="已截止">已截止</a-select-option>
              <a-select-option value="已暂停">已暂停</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='资助政策详细说明' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
      'policyText',
       { rules: [{ required: true, message: '请输入资助政策详细说明!' }] }
      ]"/>
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
  name: 'dishesEdit',
  props: {
    moduleEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.moduleEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      staffIds: [],
      staffList: [],
      fileList: [],
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
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    setFormValues ({...dishes}) {
      this.rowId = dishes.id
      let fields = ['projectName', 'projectType', 'publisherUser', 'startDate', 'endDate', 'targetGrade', 'amountPerPerson', 'quota', 'status', 'policyText']
      let obj = {}
      Object.keys(dishes).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(dishes['images'])
        }
        if ((key === 'startDate' || key === 'endDate') && dishes[key] !== null) {
          dishes[key] = moment(dishes[key])
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = dishes[key]
        }
      })
      this.form.setFieldsValue(obj)
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
        if (image.response !== undefined) {
          images.push(image.response)
        } else {
          images.push(image.name)
        }
      })
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        if (!err) {
          if (values.endDate) {
            values.endDate = moment(values.endDate).format('YYYY-MM-DD')
          }
          if (values.startDate) {
            values.startDate = moment(values.startDate).format('YYYY-MM-DD')
          }
          this.loading = true
          this.$put('/cos/project-info', {
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
