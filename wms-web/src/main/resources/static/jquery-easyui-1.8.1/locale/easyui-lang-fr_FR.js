if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = 'Page';
	$.fn.pagination.defaults.afterPageText = 'de {pages}';
	$.fn.pagination.defaults.displayMsg = 'Affichage de {from} et {to} au {total} des articles';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = "Traitement, s'il vous plaît patienter ...";
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = 'Ok';
	$.messager.defaults.cancel = 'Annuler';
}
$.map(['validatebox','textbox','passwordbox','filebox','searchbox',
		'combo','combobox','combogrid','combotree',
		'datebox','datetimebox','numberbox',
		'spinner','numberspinner','timespinner','datetimespinner'], function(plugin){
	if ($.fn[plugin]){
		$.fn[plugin].defaults.missingMessage = 'Ce champ est obligatoire.';
	}
});
if ($.fn.validatebox){
	$.fn.validatebox.defaults.rules.email.message = "S'il vous plaît entrer une adresse email valide.";
	$.fn.validatebox.defaults.rules.url.message = "S'il vous plaît entrer une URL valide.";
	$.fn.validatebox.defaults.rules.length.message = "S'il vous plaît entrez une valeur comprise entre {0} et {1}.";
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['S','M','T','W','T','F','S'];
	$.fn.calendar.defaults.months = ["Jan", "Fév", "Mar", "Avr", "Mai", "Juin", "Juil", "Aôu", "Sep", "Oct", "Nov", "Déc"];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = "Aujourd'hui";
	$.fn.datebox.defaults.closeText = 'Fermer';
	$.fn.datebox.defaults.okText = 'Ok';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText
	});
}
/**
 * international
 */
if ($.messager){
	$.messager.defaults.msg = "Attendez.......";
}

var locale = {
	selectOneRow: "Sélectionnez une ligne de données",
	errorRow: 'Édition de {row} tests ratés',
	enterInboundNumber: "Veuillez saisir le numéro d 'entrée",
	enterSku:'Choisissez la marchandise',
	enterQuantity:'Entrez la quantité de réception',
	submitRowIsNull:'Espace de données soumis',
	noChange:'Données inchangées',
	statusError:'Erreur d état',
	ownerRequired: 'Il faut choisir le propriétaire',
	selectOneSku:'Veuillez choisir la marchandise',
	selectTwoMoreRows:'Choisissez deux lignes ou plus',
	inputTargetLocation:'Veuillez saisir l adresse de la cible',
	saveFirst:'Enregistrez les données.',
	selectTemplate:'Veuillez sélectionner le modèle',
	selectFile:'Veuillez choisir',
	selectOneRole:'Veuillez choisir un rôle',
	selectCompany:'Veuillez choisir une société',
	
	submitSuccess:'Soumission réussie',
	exportTip:'Export......',
	addSuccess:'Ajouter un succès',
	importTip:'Import......',
	uploadSuccess:'upload success',
	saveSuccess:'Enregistrer le succès',
	changePasswordSuccess:'Changement de mot de passe réussi.',
	
	exitConfirm:'Quitter la page active ?',
	exportExcel:'Exporter un fichier Excel selon les conditions actuelles?',
	closeAll:'Fermer toutes les pages?',
	goOn:"Continuez?",
	deleteOneRow:'Confirmation de la suppression de {row} lignes de données ?',
	optOneRow:'Confirmation {opt} {row} lignes de données ?',
	lineConfirm:'Confirmation{type}{row} ?',
	putawayRows:'Détermination des données de {row} lignes d expédition?',
	cancelRows:'Confirmation de l annulation de {row} lignes de données行 ?',
	unLockRows:'Verrouillage confirmé. {row} lignes?',
	clearAll:'Dégagez toutes les autorisations.? ',
	offLine:'Détermination de données de {row} lignes hors ligne ?',
	initWarehouse:'Détermination de données d entreposage initialisées sur {row} lignes?',
	warehouseAction:'Détermination {action}{row} lignes?',
	
	inboundNewStatusPrint: '{inboundNumber} Seuls les nouveaux 、 InRecive Can Print',
	weightGrossMorethenNet: 'brut est supérieur au poids net',
	lessThenZero: 'La valeur ne peut être inférieure à 0',
	characterornumber: 'Seul caractère ou nombre',
	locationprintall: 'Imprimer tous les emplacements?'
}
if ($.locale){
	$.extend($.locale,locale);
}else{
	$.locale = locale;
}
