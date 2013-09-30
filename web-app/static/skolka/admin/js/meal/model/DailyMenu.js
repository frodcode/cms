initNamespace('cms.meal.model');

cms.meal.model.DailyMenu = function (data) {
    this.date = moment(data.date)
    this.menus = _.map(data.menus, function(menuData) {
        return new cms.meal.model.Menu(menuData)
    });

    this.getDayOfWeek = function() {
        return this.date.format('dddd')
    }

};