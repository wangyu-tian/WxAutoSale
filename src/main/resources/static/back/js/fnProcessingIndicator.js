jQuery.fn.dataTableExt.oApi.fnProcessingIndicator = function ( oSettings, onoff )
{
    if ( onoff === undefined ) {
        onoff = true;
    }
    this.oApi._fnProcessingDisplay( oSettings, onoff );
};