<template>
  <a-modal v-model="show" title="新增参保情况" @cancel="onClose" :width="600">
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
          <a-form-item label='参保情况' v-bind="formItemLayout">
            <a-select v-decorator="[
      'enrollmentStatus',
      { rules: [{ required: true, message: '请选择参保情况!' }] }
      ]">
              <a-select-option value="0">否</a-select-option>
              <a-select-option value="1">是</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='医疗费用总额' v-bind="formItemLayout">
            <a-input-number      style="width: 100%"
                                 v-decorator="[
      'totalMedicalExpenses',
      { rules: [{ required: true, message: '请输入医疗费用总额!' }] }
      ]"
                                 :min="0"
                                 :step="0.01"
                                 :formatter="value => `¥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                                 :parser="value => value.replace(/¥\s?|(,*)/g, '')"
            />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='实际报销金额' v-bind="formItemLayout">
            <a-input-number      style="width: 100%"
                                 v-decorator="[
      'actualAmount',
      { rules: [{ required: true, message: '请输入实际报销金额!' }] }
      ]"
                                 :min="0"
                                 :step="0.01"
                                 :formatter="value => `¥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                                 :parser="value => value.replace(/¥\s?|(,*)/g, '')"
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
          values.proofFilePath = images.length > 0 ? images.join(',') : null
          values.userId = this.currentUser.userId
          this.loading = true
          this.$post('/cos/insurance-info', {
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
