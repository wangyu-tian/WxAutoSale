$(document).ready(function(){
    /**
     * daterange
     */
    //取最近一周订单，dateRange
    daterange($('.daterange'));

    //初始化select2-form类
    select2($('.select2-form'));

    //ajax select2
    select2_ajax($('.select2-form-ajax'));

    $('input.checkbox-all').on('click', function(){
        var childClass = $(this).data('target-class');
        $('input.'+childClass).prop('checked', $(this).prop('checked'));
    });

    //init datatable
    $('.table-list').DataTable();

    $('[data-toggle="tooltip"]').tooltip();
});

function daterange($obj, config) {
    $obj = $obj || $('.daterange');
    $obj.each(function() {
        var format = $(this).attr('format') || 'YYYY-MM-DD';
        var val = $(this).val() || '';
        var arr = val.split(' - ');
        var startDate = arr[0] || moment().subtract(7, 'days');
        var endDate = arr[1] || moment().subtract(1, 'days');

        var _config = {
            startDate: startDate,
            endDate: endDate,
            showDropdowns: true,
            timePicker: format == 'YYYY-MM-DD'? false: true,
            timePicker24Hour: format == 'YYYY-MM-DD'? false: true,
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '最近7天': [moment().subtract(6, 'days'), moment()],
                '最近30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            locale: {
                format: format,
                applyLabel: '筛选',
                cancelLabel: '重置',
                fromLabel: '开始时间',
                toLabel: '结束时间',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                firstDay: 0
            },
            alwaysShowCalendars: true
        };
        for(var i in config) {
            if (typeof _config[i] == 'object') {
                for (var j in config[i]) {
                    _config[i][j] = config[i][j];
                }
            } else {
                _config[i] = config[i];
            }
        }
        $(this).daterangepicker(_config);
    });
}

function select2(obj, config) {
    obj = obj || $('.select2-form');
    config = config || {};

    obj.each(function() {
        var placeholder = $(this).attr('placeholder');
        var _config = {
            placeholder: placeholder,
            allowClear: $(this).attr('allow-clear')=='false'?false:true
        };
        for (var i in config) {
            _config[i] = config[i];
        }

        $(this).select2(_config);
    });
}

function select2_ajax($obj, config) {
    $obj = $obj || $('.select2-form-ajax');
    config = config || {};
    var _config = {
        ajax: {
            dataType: 'json',
            cache: true,
            data: function(params) {
                return {
                    q: params.term,
                    page: params.page,
                }
            },
            processResults: function(data, params) {
                params.page = params.page || 1;
                return {
                    results: data.items,
                    pagination: {
                        more: (params.page * 10) < data.total
                    }
                }
            }
        }
    };
    for (var i in config) {
        _config[i] = config[i];
    }
    select2($obj, _config);
}

/**
 * datatable
 *
 */
var g_tb_obj = {};
function dataTableList(id, url, filters, config, method) {
    var columnsFilters = filters['columns'] || {};
    var columns = [];
    var queryColumns = [];
    for(var i in columnsFilters) {
        columns.push({title: columnsFilters[i]});
        queryColumns.push(i);
    }
    var params = cloneObj(filters);
    params['columns'] = queryColumns;

    if (typeof g_tb_obj[id] != 'undefined') {
        g_tb_obj[id].destroy();
    }

    var checked = $('#'+id).data('check')||false;
    checked && columns.unshift({
        'title': "<input type='checkbox' name='checkall' onclick='checkAll($(this))'/>",
        'orderable': false
    });

    _config = {
        'columns': columns,
        //'searching': false,
        'ajax': {
            'url': url,
            'type': method || 'post',
            'data': params,
            'dataSrc': function(json) {
                var data = [];
                for(var i in json) {
                    var row = [];
                    checked && row.push("<input type='checkbox' name='checklist'/>");
                    for(var j in columnsFilters) {
                        row.push(json[i][j]);
                    }
                    data.push(row);
                }
                return data;
            }
        },
        order: [[checked?1:0, 'desc']],
        drawCallback: function(settings) {
            //checklist 暂时已勾选个数
            $("input[name='checklist']").off('click').on('click', function() {
                showSelectedNum();
            });
        }
    };

    //自定义配置
    config = config || {};
    for(var k in config) {
        _config[k] = config[k];
    }

    g_tb_obj[id] = $('#'+id).DataTable(_config);
}

function showSelectedNum()
{
    if (!$('#example_length').length) {
        return true;
    }
    var __num = $("input[name='checklist']:checked").length;
    var __str = '已勾选'+__num+'个';
    var __v = $('#example_length').children('#datatable-checkbox-length')
    if (__v.length) {
        __v.html(__str);
    } else {
        $('#example_length').append('<span id="datatable-checkbox-length" style="margin-left:15px;">'+__str+'</span>');
    }
}

function getFormValues(formObj)
{
    if (typeof formObj == 'string') {
        formObj = $('#'+formObj);
    }
    var params = {};
    var elements = formObj.serializeArray();
    var multiParams = {};

    for(var i =0; i<elements.length; i++) {
        if (typeof multiParams[elements[i]['name']] == 'undefined') {
            multiParams[elements[i]['name']] = [];
        }
        multiParams[elements[i]['name']].push(elements[i]['value']);
    }

    for (var i in multiParams) {
        if (multiParams[i].length ==1) {
            params[i] = multiParams[i][0];
        } else {
            params[i] = multiParams[i];
        }
    }

    return params;
}

/**
 * highchart
 *
 */
function highChartInit(id, data, mapNames, config)
{
    var _config = {
        chart: {
            type: 'area'
        },
        title: {
            text: null,
            x: -20 //center
        },
        yAxis: {
            title: {
                text: null
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            shared: true,
            valueSuffix: ''
        },
        plotOptions: {
            area: {
                // stacking: 'normal',
                // lineColor: '#666666',
                // lineWidth: 1,
                fillOpacity: 0.35,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        'xAxis': {
            categories: []
        },
        'series': []
    };
    config = config|| {};
    for(var i in config) {
        _config[i] = config[i];
    }

    //排序
    var days = [];
    for (var day in data) {
        days.push(day);
    }
    days.sort();

    for (var i=0; i<days.length; i++) {
        _config['xAxis']['categories'].push(days[i]);
    }

    if (typeof data[days[0]] != 'object') {
        var series = [];
        for (var i=0; i<days.length; i++) {
            var day = days[i];
            series.push(data[day]);
        }
        _config['series'].push({name: mapNames||'', data: series, showInLegend: false});
        return $('#'+id).highcharts(_config);
    }

    var series = {};
    for (var i=0; i<days.length; i++) {
        var day = days[i];
        for(var type in data[day]) {
            if (typeof series[type] == 'undefined') {
                series[type] = [];
            }
            series[type].push(data[day][type]);
        }
    }

    mapNames = mapNames||{};
    for(var i in series) {
        _config['series'].push({name: mapNames[i]||i, data: series[i]});
    }

    $('#'+id).highcharts(_config);
}

function cloneObj(obj)
{
    var newObj = {};
    for (var i in obj) {
        newObj[i] = obj[i];
    }
    return newObj;
}

function checkAll(obj)
{
    $("input[name='checklist']").prop('checked', obj.prop('checked'));
    showSelectedNum();
}


function removeData(url, callback) {
    bootbox.confirm("确认删除？", function(result){
        if (result){
            $.get(url, function(data){
                if (!data.status) {
                    bootbox.alert('删除成功！', function() {
                        if (callback) {
                            callback();
                        } else {
                            window.location.reload();
                        }
                    });
                } else {
                    bootbox.alert(data.msg);
                }
            }, 'json');
        }
    });
    return false;
}

function batchOpData(url, callback) {
    $.get(url, function(data){
        if (!data.status) {
            bootbox.alert('操作成功！', function(){
                if (callback) {
                    callback();
                } else {
                    window.location.reload();
                }
            });
        } else {
            bootbox.alert(data.msg);
        }
    }, 'json');

    return false;
}


function copyData(url, callback) {
    $.get(url, function(data){
        if (!data.status) {
            bootbox.alert('复制成功！', function() {
                if (callback) {
                    callback();
                } else {
                    window.location.reload();
                }
            });
        } else {
            bootbox.alert(data.msg);
        }
    }, 'json');

    return false;
}


// modal dailog
// 模态对话框 n 加载页面 t title w:wid宽度
function openSubmitModal(url, title, callback, width, $form) {
    var wd = {
        'small': BootstrapDialog.SIZE_SMALL,
        'normal': BootstrapDialog.SIZE_NORMAL,
        'large': BootstrapDialog.SIZE_LARGE,
        'wide': BootstrapDialog.SIZE_WIDE,
    };
    width = width || 'normal';
    var _modal = BootstrapDialog.show({
        title: title,
        size: wd[width],
        message: $('<div></div>').load(url),
        buttons: [{
            id: 'form-modal-save',
            label: '保存',
            cssClass: 'btn-success ladda-button',
            action: function(dialogItself){
                var $form = $form || $('#form-modal');
                //数据required验证
                var flag = true;
                $form.find('[required]').each(function() {
                    if (!$(this).val()) {
                        bootbox.alert('请填完必填项');
                        return flag = false;
                    }
                });
                if (!flag) {
                    return false;
                }
                //form onsubmit方法验证
                var validFunc = $.trim($form.attr('onsubmit')).replace(/(^return)|(\;+$)/g, '');
                if (validFunc && !eval('('+validFunc+')')) {
                    return false;
                }
                var l = null;
                var intervalProgress = null;
                var config = {
                    type: $form.attr('method'),
                    url: $form.attr('action'),
                    data: getFormValues($form),
                    beforeSend: function (XMLHttpRequest) {
                        $('#form-modal-save').attr('data-style', 'expand-right');
                        l = Ladda.create(document.querySelector('#form-modal-save'));
                        l.start();
                        var progress = 0;
                        var intervalProgress = setInterval( function() {
                            progress = Math.min(progress + Math.random() * 0.1, 0.9);
                            l.setProgress( progress );
                        }, 200);
                    },
                    complete: function(XMLHttpRequest, textStatus) {
                        l.setProgress(1);
                        clearInterval(intervalProgress);
                        l.stop();
                    },
                    success: function(data, status) {
                        if (data.msg) {
                            bootbox.alert(data.msg, function(){
                                if (data.status != 0){
                                    return true;
                                }
                                dialogItself.close();
                                if (callback) {
                                    callback();
                                } else {
                                    window.location.reload();
                                }
                            });
                        } else if (data.status != 0){
                            return false;
                        } else {
                            dialogItself.close();
                            if (callback) {
                                callback();
                            } else {
                                window.location.reload();
                            }
                        }
                    },
                    error: function() {
                        bootbox.alert("ajax返回失败!");
                        return false;
                    }
                };

                //上传文件
                if ($form.attr('enctype')=='multipart/form-data') {
                    config['data'] = new FormData($form[0]);
                    config['contentType'] = false;
                    config['processData'] = false;
                    config['cache'] = false;
                }
                $.ajax(config);
            }
        }, {
            label: '取消',
            cssClass: 'btn-warning',
            action: function(dialogItself) {
                dialogItself.close();
            }
        }]
    });
    _modal.getModal().removeAttr('tabindex');
    return _modal;
}

/**
 * 打开modal,非form提交
 */
function openModal(url, title, width) {
    var wd = {
        'small': BootstrapDialog.SIZE_SMALL,
        'normal': BootstrapDialog.SIZE_NORMAL,
        'large': BootstrapDialog.SIZE_LARGE,
        'wide': BootstrapDialog.SIZE_WIDE,
    };
    width = width || 'normal';
    var _modal = BootstrapDialog.show({
        title: title,
        size: wd[width],
        message: $('<div></div>').load(url),
        buttons: [{
            label: '关闭',
            cssClass: 'btn-warning',
            action: function(dialogItself) {
                dialogItself.close();
            }
        }]
    });
    _modal.getModal().removeAttr('tabindex');
    return _modal;
}

/**
 * 千分位
 * @param {[type]} number [description]
 */
function addKannma(number) {
    if (number == 0) {
        return 0;
    }
    var num = number + "";
    num = num.replace(new RegExp(",","g"),"");
    // 正负号处理
    var symble = "";
    if(/^([-+]).*$/.test(num)) {
        symble = num.replace(/^([-+]).*$/,"$1");
        num = num.replace(/^([-+])(.*)$/,"$2");
    }

    if(/^[0-9]+(\.[0-9]+)?$/.test(num)) {
        var num = num.replace(new RegExp("^[0]+","g"),"");
        if(/^\./.test(num)) {
            num = "0" + num;
        }

        var decimal = num.replace(/^[0-9]+(\.[0-9]+)?$/,"$1");
        var integer= num.replace(/^([0-9]+)(\.[0-9]+)?$/,"$1");

        var re=/(\d+)(\d{3})/;

        while(re.test(integer)){
            integer = integer.replace(re,"$1,$2");
        }
        return symble + integer + decimal;

    } else {
        return number;
    }
}

function truncate(str, lenth, suffix)
{
    if (str.length < lenth) {
        return str;
    }
    suffix = suffix || '...';
    return str.substr(0, lenth)+suffix;
}

function array_sum(arr) {
    var num = 0;
    for (var i in arr) {
        num += parseInt(arr[i]);
    }
    return num;
}

