var options = {
	chart: {
		height: 210,
		type: 'line',
		toolbar: {
			show: false,
		},
	},
	dataLabels: {
		enabled: false
	},
	stroke: {
		curve: 'smooth',
		width: 4
	},
	series: [{
		name: 'Sales',
		data: [200, 450, 200, 400]
	}],
	grid: {
		row: {
			colors: ['transparent'], // takes an array which will be repeated on columns
			opacity: 0.5,
		},
		xaxis: {
      lines: {
        show: false
      }
    },   
    yaxis: {
      lines: {
        show: false
      }
    },
	},
	xaxis: {
		categories: ['Electronics', 'Grocery', 'Beauty', 'Toys'],
		// labels: {
	 //    show: false
	 //  }
	},
	colors: ['#225de4', '#999999'],
	markers: {
		size: 7,
		opacity: 0.2,
		colors: ["#225de4"],
		strokeColor: "#ffffff",
		strokeWidth: 2,
		hover: {
			size: 10,
		}
	},
	tooltip: {
		y: {
			formatter: function(val) {
				return  "$" + val + 'k'
			}
		}
	},
}

var chart = new ApexCharts(
	document.querySelector("#compare-sales"),
	options
);

chart.render();
