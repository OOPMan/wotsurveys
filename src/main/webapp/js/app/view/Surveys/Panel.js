
/**
 * This file is part of WoTSurveys
 *
 * It is subject to the license terms in the LICENSE.txt file found in the top-level directory of this distribution and
 * at http://eclipse.org/org/documents/epl-v10.php.
 *
 * No part of WoTSurveys, including this file, may be copied, modified, propagated, or distributed except according
 * to the terms contained in the LICENSE file.
 */

Ext.define('WoTSurveys.view.Surveys.Panel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.SurveyPanel',
    store: 'Survey',
    layout: 'card', //TODO: Complete card layout details

    initComponent: function() {
        this.callParent(arguments);
    }

    //TODO: Add code to handle incoming events?
});
