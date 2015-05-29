(function(window) {
  function DateSelector(opts) {
    this.init(opts);
  }
  var fn  = DateSelector.prototype;
  fn.init = function(opts) {
    var yearEle   = opts.yearEle;
    var monthEle  = opts.monthEle;
    var dayEle    = opts.dayEle;
    var hourEle   = opts.hourEle;
    var minuteEle = opts.minuteEle;
    initYear(yearEle);
    initMonth(monthEle);
    initHour(hourEle);
    initMinute(minuteEle);
    function initYear(ele) {
      if (!ele) {
        return;
      }
      ele.innerHTML = '';
      var minY = 2000;
      var maxY = (new Date()).getFullYear();
      append(maxY, ele, minY);
      ele.addEventListener('change', function() {
        initMonth(monthEle);
      });
      ele.value = maxY;
    }
    function initMonth(ele) {
      if (!ele) {
        return;
      }
      ele.innerHTML = '';
      append(12, ele);
      initDay();
      ele.addEventListener('change', function() {
        initDay();
      }, false);
    }

    function initDay(ele) {
        if (!ele) {
            return;
        }
      var ele = dayEle;
      ele.innerHTML = '';
      var v = parseInt(monthEle.value);
      if ([1,3,5,7,8,10,12].indexOf(v) != -1) {
        num = 31;
      }
      else if (v == 2) {
        var vy = parseInt(yearEle.value);
        if ((vy / 4 == 0 && vy % 100 != 0) || (vy % 400 == 0)) {
          num = 29;
        } else {
          num = 28;
        }
      }
      else {
        num = 30;
      }
      append(num, ele);
    }
    function initHour(ele) {
        if (!ele) {
            return;
        }
      ele.innerHTML = '';
      append(24, ele, 0,true);
    }
    function initMinute(ele) {
        if (!ele) {
            return;
        }
      ele.innerHTML = '';
      append(60,ele,0, true);
    }

    function append(num, ele, min, time) {
      for (var i = min == undefined ? 1 : min ; i <= num; i ++) {
        var option       = document.createElement('option');
        option.value     = i;
        if (time) {
          if (i < 10) {
            i = '0' + i;
          }
        }
        option.innerHTML = i;
        ele.appendChild(option);
      }
    }
  };
  return window.DateSelector = function(opts) {
    return new DateSelector(opts);
  };
})(window);
