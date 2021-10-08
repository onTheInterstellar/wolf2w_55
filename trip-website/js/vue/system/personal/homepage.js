var vue = new Vue({
    el:"#app",
    data:{
        owner:{},
        visitors:[],
        totalView:200,
        todayView:20
    },
    mounted:function () {
        var param = getParams();
        var ownerId = param.ownerId;
        ajaxGet("/users/get", {id:ownerId},function (data) {
            vue.owner = data.data;
            ajaxGet("/users/visitors", {id:ownerId},function (data) {
                var map = data.data;
                vue.visitors = map.visitors;
                vue.totalView = map.totalView;
                vue.todayView = map.todayView;
            })
        });




    }
});

