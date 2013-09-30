initNamespace('cms.meal.model');

cms.meal.model.Menu = function (data) {
    this.date = moment(data.date)
    this.name = data.name;
    this.type = data.type
    this.typeId = data.typeId

};