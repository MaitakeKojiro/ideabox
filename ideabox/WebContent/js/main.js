// floatingButoon.jsp
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems, options);
	var instance = M.Tooltip.getInstance(elems);
	instance.open();
  });

  $(document).ready(function(){
    $('.tooltipped').tooltip();
  });