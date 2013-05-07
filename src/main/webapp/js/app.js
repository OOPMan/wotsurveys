
/**
 * This file is part of WoTSurveys
 *
 * It is subject to the license terms in the LICENSE.txt file found in the top-level directory of this distribution and
 * at http://eclipse.org/org/documents/epl-v10.php.
 *
 * No part of WoTSurveys, including this file, may be copied, modified, propagated, or distributed except according
 * to the terms contained in the LICENSE file.
 */

Ext.application({

    name: 'WoTSurveys',
    requires: ['Ext.container.Viewport'],
    appFolder: 'js/app',
    controllers: ['Surveys'],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'SurveyList'
                }
            ]
        });
    }
});