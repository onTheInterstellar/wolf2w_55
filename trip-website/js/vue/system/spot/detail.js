var vue = new Vue({
    el:"#app",
    data:{
        spot:{}
    },
    methods:{
    },
    mounted:function () {
        var param = getParams();
        var id = param.id;
        //景点明细
        var _this = this;

        ajaxGet("/spots/detail", {id:id}, function (data) {
            _this.spot = data.data;
        })

    }
});

