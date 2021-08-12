var vue = new Vue({
    el:"#app",
    data:{
        spots:[]
    },
    methods:{
    },
    mounted:function () {
        //景点列表
        var _this = this;

        ajaxGet("/spots/list", {}, function (data) {
            _this.spots = data.data;
            console.log(data)
        })
    }
});

