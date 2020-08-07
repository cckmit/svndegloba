import {Component} from "@angular/core";


import { AllModules, ColumnApi, GridApi, Module } from '@ag-grid-enterprise/all-modules';
import "@ag-grid-enterprise/all-modules/dist/styles/ag-grid.css";
import "@ag-grid-enterprise/all-modules/dist/styles/ag-theme-alpine.css";


import {MockServer} from '../grid/mock-server';
import { MockServerService } from "app/providers/rxjs-component/mock-server-service/mockServer.service";

import Utils from '../utils'

@Component({
  selector: 'rich-grid-hotel',
  template: `<ag-grid-angular
			#agGrid 
			style="width: 1000px; height: 500px;"
			id="myGrid" 
			class="ag-theme-alpine" [modules]="modules"
			[columnDefs]="columnDefs" 
			[defaultColDef]="defaultColDef"
			[enableRangeSelection]="true" 
			[getRowNodeId]="getRowNodeId"
			[rowData]="rowData" 
			(gridReady)="onGridReady($event)">
		</ag-grid-angular>`,
})
export class HotelsComponent {
   

	private gridApi: any;
	private gridColumnApi: any;
	
	public modules: Module[] = AllModules;
	public columnDefs: any;
	public defaultColDef: any;
	public getRowNodeId: any;
	public rowData: any[];


  constructor() { 
		
	
	this.columnDefs = [
		{
			field: 'code',
			maxwidth: 90,
		},
		{
			field: 'name',
			maxwidth: 200,
		},
		{
			field: 'bid',
			cellClass: 'cell-number',
			valueFormatter: numberFormatter,
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
		{
			field: 'mid',
			cellClass: 'cell-number',
			valueFormatter: numberFormatter,
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
		{
			field: 'ask',
			cellClass: 'cell-number',
			valueFormatter: numberFormatter,
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
		
		{
			field: 'volume',
			cellClass: 'cell-number',			
			cellRenderer: 'agAnimateShowChangeCellRenderer',
		},
	];
	this.defaultColDef = {
		flex: 1,
		minWidth: 100,
		resizable: true,
	};
	
	
	/* Una clau �nica per fila: ho fem aprofitant la devoluci� de getRowNodeId */
	this.getRowNodeId = function(data: { code: any; }) {
	
		return data.code;
	};
}


onGridReady(params)  {
	this.gridApi = params.api;
	this.gridColumnApi = params.columnApi;

alert(Utils.doSomething("test"));



	/* Observable */
	var mockServer = new MockServer();		

	var	initialLoad$ = mockServer.initialLoad();

	var updates$ = mockServer.byRowUpdates();
	
		initialLoad$.subscribe(function(rowData) {
			
			params.api.setRowData(rowData);
				
			
		});
		
		updates$.subscribe(function(updates: any) {
				
				/* Una manera de donar a con�ixer a Ag-Grid el tipus d�actualitzaci� que fem - 
				per a aix� fem servir el m�tode Transaction: */ 
				return params.api.applyTransaction({ update: updates});
			});	
		
	}	
}
	
function numberFormatter(params) {
	if (typeof params.value === 'number') {
		return params.value.toFixed(2);
	}
	return params.value;
}

