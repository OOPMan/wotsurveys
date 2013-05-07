
/**
 * This file is part of WoTSurveys
 *
 * It is subject to the license terms in the LICENSE.txt file found in the top-level directory of this distribution and
 * at http://eclipse.org/org/documents/epl-v10.php.
 *
 * No part of WoTSurveys, including this file, may be copied, modified, propagated, or distributed except according
 * to the terms contained in the LICENSE file.
 */

Ext.define('WoTSurveys.view.Surveys.List', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.SurveyList',
    title: 'All Surveys',
    store: 'Surveys',

    initComponent: function () {
        this.columns = [
            { header: 'ID', dataIndex: 'id', flex: 1 },
            { header: 'Name', dataIndex: 'name', flex: 1 }
        ];

        this.callParent(arguments);
    }

});

