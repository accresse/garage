<!DOCTYPE html>
<html>
<head>
<title>Garage Manager</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.css" />
<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.js"></script>
<script type="text/javascript">

$(document).ready(
		function() {
       		$('#loading').hide();
        	$("#toggleButton").click(toggleDoor);
			pollDoorStatus(); 
		});

var toggleDoor = function() {
    $.post('toggle').done(function(){ alert('Done'); }).fail(function(jqXHR, textStatus, errorThrown){ alert('Failed: '+errorThrown); });
};

function pollDoorStatus(){
		$('#loading').show();
		$.get('status').done(updateStatusFields).always(function() {
			$('#loading').hide(); 
			setTimeout(pollDoorStatus, 5000); });
}

var updateStatusFields = function(data) {
	var status = data;
	var color = status=="OPEN"?"#cc0000":"#00cc00";
	$('#currentStatus').text(status);
	$('#currentStatus').css('color',color);
	$('#lastUpdate').text(new Date().toLocaleString());
};

</script>
</head>

<body>
	<div data-role="page">

		<div data-role="header">
			<h1>Garage Manager</h1>
		</div>

		<div role="main" class="ui-content">
			<div class="ui-body ui-body-a ui-corner-all" style="text-align: center;">
				<h1 id="currentStatus">UNKNOWN</h1>
			</div>
			<div style="font-size: small; text-align: center;">
				<img id="loading" src="resources/spinner.gif"/>
				<span style="font-weight: bold;">As of:</span>
				<span id="lastUpdate" style="font-style: italic;">Never</span>
			</div>
			<button id="toggleButton" class="ui-btn ui-corner-all ui-shadow">Toggle</button>
		</div>

	</div>
</body>
</html>