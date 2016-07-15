$(document).ready(function(){
		$('.removeLk').on('click', function(e){
			e.preventDefault();
			e.stopPropagation();
			var input = confirm('You sure to remove this product ?');
			if(input){
				$.ajax({
					method: 'DELETE',
					url: e.currentTarget.href,
					success: function (results){						
						setTimeout(function(){ location.reload(true); }, 500);						
					},
					error: function( jqXHR, textStatus, textStatus ){
						console.log(textStatus);
						console.log(textStatus);
						console.log(jqXHR);
					}
				});			
			}
		});
});		