function ajaxStart() {
	document.body.style.cursor = 'wait';
}

function ajaxComplete() {
	document.body.style.cursor = 'auto';
}


function searchImportCost() {
	alert(1);
	searchImportCostSync();
	alert(2);
}