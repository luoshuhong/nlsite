<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <!DOCTYPE html> -->
<html lang="zh-CN">
  <head>
    <title>new leader</title>
    	<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts.js"></script>
		<script type="text/javascript" src="${ctx}/js/highcharts-4.1.8/highcharts-more.js"></script>
  </head>
  
	<body>
  		<!-- 图表区域  -->
		<div id="container" style=""> </div>
	</body>
	<script type="text/javascript">
	$(function () {                                                                     
	    $(document).ready(function() {                                                  
	        Highcharts.setOptions({                                                     
	            global: {                                                               
	                useUTC: false                                                       
	            }                                                                       
	        });                                                                         
	                                                                                    
	        var chart;                                                                  
	        $('#container').highcharts({                                                
	            chart: {                                                                
	                type: 'spline',                                                     
	                animation: Highcharts.svg, // don't animate in old IE               
	                marginRight: 10,                                                    
	                events: {                                                           
	                    load: function() {                                              
	                        // set up the updating of the chart each second             
	                        var series = this.series[0];                                
	                        setInterval(function() {                                    
	                            var x = (new Date()).getTime(), // current time         
	                                y = Math.random(); 
	                            series.addPoint([x, y], true, true);                    
	                        }, 1000);                                                   
	                    }                                                               
	                }                                                                   
	            },                                                                      
	            title: {                                                                
	                text: 'Live random data'                                            
	            },                                                                      
	            xAxis: {                                                                
	                type: 'datetime',                                                   
	                tickPixelInterval: 150                                              
	            },                                                                      
	            yAxis: {                                                                
	                title: {                                                            
	                    text: 'Value'                                                   
	                },                                                                  
	                plotLines: [{                                                       
	                    value: 0,                                                       
	                    width: 1,                                                       
	                    color: '#808080'                                                
	                }]                                                                  
	            },                                                                      
	            tooltip: {                                                              
	                formatter: function() {                                             
	                        return '<b>'+ this.series.name +'</b><br/>'+                
	                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
	                        Highcharts.numberFormat(this.y, 2);                         
	                }                                                                   
	            },                                                                      
	            legend: {                                                               
	                enabled: false                                                      
	            },                                                                      
	            exporting: {                                                            
	                enabled: false                                                      
	            },                                                                      
	            series: [{                                                              
	                name: 'Random data',                                                
	                data: (function() {                                                 
	                    // generate an array of random data                             
	                    var data = [],                                                  
	                        time = (new Date()).getTime(),                              
	                        i;                                                          
	                                                                                    
	                    for (i = -19; i <= 0; i++) {                                    
	                        data.push({                                                 
	                            x: time + i * 1000,                                     
	                            y: Math.random()                                        
	                        });                                                         
	                    }                                                               
	                    return data;                                                    
	                })()                                                                
	            }]                                                                      
	        });                                                                         
	    });                                                                             
	                                                                                    
	});             
	
	</script>
</html> 