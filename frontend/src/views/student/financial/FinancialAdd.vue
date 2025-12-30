<template>
  <a-modal v-model="show" title="新增经济情况" @cancel="onClose" :width="600">
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
          <a-form-item label='是否认定为困难生' v-bind="formItemLayout">
            <a-select v-decorator="[
              'isHardship',
              { rules: [{ required: true, message: '请选择是否认定为困难生!' }] }
              ]">
              <a-select-option value="0">否</a-select-option>
              <a-select-option value="1">是</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='家庭年收入' v-bind="formItemLayout">
            <a-input-number
              style="width: 100%"
              v-decorator="[
              'familyIncome',
              { rules: [{ required: true, message: '请输入家庭年收入!' }] }
              ]"
              :min="0"
              :step="0.01"
              :formatter="value => `¥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
              :parser="value => value.replace(/¥\s?|(,*)/g, '')"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='家庭人口数' v-bind="formItemLayout">
            <a-input-number
              style="width: 100%"
              v-decorator="[
              'familyMembers',
              { rules: [{ required: true, message: '请输入家庭人口数!' }] }
              ]"
              :min="1"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='家庭户口' v-bind="formItemLayout">
            <a-select v-decorator="[
              'registration',
              { rules: [{ required: true, message: '请选择项目类型!' }] }
              ]">
              <a-select-option value="0">城镇</a-select-option>
              <a-select-option value="1">农村</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='收入来源' v-bind="formItemLayout">
            <a-input v-decorator="[
            'sourcesIncome',
            { rules: [{ required: true, message: '请输入收入来源!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='家庭地址' v-bind="formItemLayout">
            <a-input v-decorator="[
            'address',
            { rules: [{ required: true, message: '请输入家庭地址!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='邮政编码' v-bind="formItemLayout">
            <a-input v-decorator="[
            'postalCode',
            { rules: [{ required: true, message: '请输入邮政编码!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='证明文件' v-bind="formItemLayout">
            <a-upload-dragger
              name="avatar"
              action="http://127.0.0.1:9527/file/fileUpload/"
              list-type="picture-card"
              :file-list="fileList"
              @change="picHandleChange"
              class="upload-dragger"
            >
              <p class="ant-upload-drag-icon">
                <a-icon type="inbox" class="upload-icon" />
              </p>
              <p class="ant-upload-text">点击或拖拽文件到此区域上传</p>
              <p class="ant-upload-hint">支持PDF、Word格式文件</p>
            </a-upload-dragger>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='家庭情况' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
            'remark',
             { rules: [{ required: true, message: '请输入家庭情况!' }] }
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
          values.userId = this.currentUser.userId
          values.proofFilePath = images.length > 0 ? images.join(',') : null
          this.loading = true
          this.$post('/cos/financial-status-info', {
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
