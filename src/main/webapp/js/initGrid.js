window.onload = function() {
  var gridWidth = (document.documentElement.clientWidth - 2) / 3;
  var grids     = document.querySelectorAll('.grid');
  for (var i = 0 ; i < grids.length; i ++) {
    grids[i].style.width = grids[i].style.height = gridWidth + 'px';
  }
};
