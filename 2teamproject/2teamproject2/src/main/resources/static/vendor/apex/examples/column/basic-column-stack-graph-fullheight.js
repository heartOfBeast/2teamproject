var options = {
  chart: {
    height: 350,
    type: 'bar',
    stacked: true,
    stackType: '100%',
    toolbar: {
      show: false
    },
    zoom: {
      enabled: true
    }
  },
  responsive: [{
    breakpoint: 480,
    options: {
      legend: {
        position: 'bottom',
        offsetX: -10,
        offsetY: 0
      }
    }
  }],
  plotOptions: {
    bar: {
      horizontal: false,
    },
  },
  dataLabels: {
    enabled: true
  },
  series: [{
    name: '식료품',
    data: [44, 55, 41, 67, 22, 43]
  },{
    name: '비품',
    data: [13, 23, 20, 8, 13, 27]
  },{
    name: '임금',
    data: [11, 17, 15, 15, 21, 14]
  },{
    name: '생필품',
    data: [21, 7, 25, 13, 22, 8]
  }],
  xaxis: {
    type: 'datetime',
    categories: ['01/01/2011 GMT', '01/02/2011 GMT', '01/03/2011 GMT', '01/04/2011 GMT', '01/05/2011 GMT', '01/06/2011 GMT'],
  },
  legend: {
    position: 'top',
    offsetY: 10
  },
  fill: {
    opacity: 1
  },
  colors: ['#1646b3', '#194eca', '#225de4', '#4274e8', '#628cec', '#81a3f0'],
}
var chart = new ApexCharts(
  document.querySelector("#basic-column-stack-graph-fullheight"),
  options
);
chart.render();


