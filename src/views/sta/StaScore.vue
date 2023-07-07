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

export default {
  name: "StaScore",
  data(){
    return{
      integralCount: [],
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
    await this.findIntegralCountList();
    // 接下来的使用就跟之前一样，初始化图表，设置配置项
    let myChart = echarts.init(document.getElementById('pie'));
    const labelRight = {
      position: 'right'
    };
    let option = {
      title: {
        text: '员工奖惩统计'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      grid: {
        top: 80,
        bottom: 30
      },
      xAxis: {
        type: 'value',
        position: 'top',
        splitLine: {
          lineStyle: {
            type: 'dashed'
          }
        }
      },
      yAxis: {
        type: 'category',
        axisLine: { show: false },
        axisLabel: { show: false },
        axisTick: { show: false },
        splitLine: { show: false },
        data:this.integralCount,
      },
      series: [
        {
          name:  '奖惩积分',
          type: 'bar',
          stack: 'Total',
          label: {
            show: true,
            formatter: '{b}'
          },
          data: this.integralCount,
        }
      ]
    };
    myChart.setOption(option);
  },
  methods: {
    async findIntegralCountList() {
      await this.getRequest('/employee/integral').then(resp => {
        let res = JSON.parse(JSON.stringify(resp).replaceAll("ecPoint", "name").replaceAll("num", "value"));
        if (resp) {
          //console.log(res)
          this.integralCount = res;
        }
      })
    },
  }
}
</script>

<style scoped>
#pie{
  width: 1800px;
  height: 800px;
  margin: 40px auto;
}
</style>