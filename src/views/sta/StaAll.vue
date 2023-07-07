<template>
  <div id="pie">
  </div>
</template>

<script>
// 引入 echarts 核心模块，核心模块提供了 echarts 使用必须要的接口。
import * as echarts from 'echarts/core';
// 引入柱状图图表，图表后缀都为 Chart
import {BarChart} from 'echarts/charts';
// 引入提示框，标题，直角坐标系，数据集，内置数据转换器组件，组件后缀都为 Component
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  DatasetComponent,
  TransformComponent
} from 'echarts/components';
// 标签自动布局、全局过渡动画等特性
import {LabelLayout, UniversalTransition} from 'echarts/features';
// 引入 Canvas 渲染器，注意引入 CanvasRenderer 或者 SVGRenderer 是必须的一步
import {CanvasRenderer} from 'echarts/renderers';
import axios from "axios";
import res from "core-js/internals/is-forced";

export default {
  name: "StaAll",
 data() {
    return{
      jobLevelCount: [],
      tiptopDegreeCount:[],
      departmentCount:[]
    }
},
  created() {
    // 注册必须的组件
    echarts.use([
      TitleComponent,
      TooltipComponent,
      GridComponent,
      DatasetComponent,
      TransformComponent,
      BarChart,
      LabelLayout,
      UniversalTransition,
      CanvasRenderer
    ]);
  },
  async mounted() {
    // 查找统计数据
    // 前后端交互：
    /*
     1. 请求后端，查看Network是否请求，请求是否报错，数据是否正常返回
     2. 如果是ajax请求，可能会出现异步的问题，主线程会继续往下执行，此时调用this.data肯定是
        旧的数据，而不是我们请求之后的数据，所以我们需要await等待方法执行完成之后再进行下面的代码
     3. 将拿到的数据入参到vue的data里面，然后就渲染数据即可
     提醒：要根据后端的请求类型以及入参修改自己的方法以及参数（getRequest：后端为get请求）
     */
    await this.findJobLevelCountList();
    await this.findDepartmentCountList();
    await this.findTiptopDegreeCountList();
    // 接下来的使用就跟之前一样，初始化图表，设置配置项
    let myChart = echarts.init(document.getElementById('pie'));
    let option = {
      title: {
        text: '数据统计表',
        subtext: '职业，学历，部门',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        left: 'center',
        top: 'bottom',

      },
      toolbox: {
        show: true,

      },
      series: [
        {
          name: '职业人数比例',
          type: 'pie',
          radius: [20, 160],
          center: ['15%', '50%'],
          roseType: 'radius',
          itemStyle: {
            borderRadius:80
          },
          label: {
            normal: {
              show: true,
              formatter: '{b}: {c}({d}%)' //自定义显示格式(b:name, c:value, d:百分比)
            }
          },
          emphasis: {
            label: {
              show: true
            }
          },
          data: this.jobLevelCount,

        },
        {
          name: '学历人数比例',
          type: 'pie',
          radius: [20, 160],
          center: ['45%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 65
          },
          label: {
            normal: {
              show: true,
              formatter: '{b}: {c}({d}%)' //自定义显示格式(b:name, c:value, d:百分比)
            }
          },
          data: this.tiptopDegreeCount,
        },
        {
          name: '部门人数比例',
          type: 'pie',
          radius: [20, 160],
          center: ['75%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 50
          },
          label: {
            normal: {
              show: true,
              formatter: '{b}: {c}({d}%)' //自定义显示格式(b:name, c:value, d:百分比)
            }
          },
          data: this.departmentCount,
        }
      ]
    };
    myChart.setOption(option);
  },
  methods: {
    async findJobLevelCountList() {
      await this.getRequest('/employee/countJobLevelId').then(resp => {
        let res = JSON.parse(JSON.stringify(resp).replaceAll("jobLevelId", "name").replaceAll("num", "value"));
        if (resp) {
          // console.log(res)
          this.jobLevelCount = res;
        }
      })
    },
    async findTiptopDegreeCountList() {
      await this.getRequest('/employee/count').then(resp => {
        let res = JSON.parse(JSON.stringify(resp).replaceAll("tiptopDegree", "name").replaceAll("num", "value"));
        if (resp) {
          // console.log(res)
          this.tiptopDegreeCount = res;
        }
      })
    },
    async findDepartmentCountList() {
      await this.getRequest('/employee/countDepartment').then(resp => {
        let res = JSON.parse(JSON.stringify(resp).replaceAll("departmentId", "name").replaceAll("num", "value"));
        if (resp) {
          // console.log(res)
          this.departmentCount = res;
        }
      })
    },
  },

};
</script>

<style scoped>
#pie {
  width: 2000px;
  height: 700px;
  margin:50px auto;
}
</style>