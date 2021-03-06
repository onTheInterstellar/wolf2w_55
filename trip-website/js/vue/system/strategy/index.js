var vue = new Vue({
    el:"#app",
    data:{
        abroadCds:[],
        chinaCds:[],
        hotCds:[],
        themeCds:[],

        chinas:[],
        abroads:[],
        themes:[],
        page:{},
        commends:[]
    },
    methods:{
        strategySearch:function (e,type, refid, name) {
            $(".gl_wrap ol li a").css("background", "none").css("color", "#666").removeClass("s-on");
            $(e.currentTarget).css("background", "#ff9d00").css("color", "#fff").addClass("s-on");;
            if(name){
                $("#currentNav").html(name).css("color", "red");
                $("#searchForm input[name='type']").val(type);
                $("#searchForm input[name='refid']").val(refid);
                $("#searchForm input[name='orderBy']").val($(".upt_on").data("v"));
                this.doPage(1);
            }
        },
        orderSearch:function (e, type) {
            $(".uptime_p a").removeClass("upt_on");
            $(e.currentTarget).addClass("upt_on");

            $("#searchForm input[name='type']").val($(".s-on").data("type"));
            $("#searchForm input[name='refid']").val($(".s-on").data("refid"));
            $("#searchForm input[name='orderBy']").val($(".upt_on").data("v"));
            this.doPage(1);

        },
        doPage:function (page) {

            $("#searchForm input[name='currentPage']").val(page);
            ajaxGet("/strategies/query",$("#searchForm").serialize(), function (data) {
                vue.page = data.data;
                buildPage(vue.page.current, vue.page.pages,vue.doPage);
            })
        }
    },
    filters:{
        dateFormat:function(date){
            return dateFormat(date, "YYYY-MM-DD HH:mm:ss")
        }
    },
    mounted:function () {
        var _this = this;

        ajaxGet('/strategies/rank', {type:3}, function (data) {
            _this.hotCds = data.data;
        });

        ajaxGet('/strategies/rank', {type:2}, function (data) {
            _this.chinaCds = data.data;
        });

        ajaxGet('/strategies/rank', {type:1}, function (data) {
            _this.abroadCds = data.data;
        });

        ajaxGet("/strategies/condition", {type:1}, function (data) {
            _this.abroads = data.data;
        });

        ajaxGet("/strategies/condition", {type:2}, function (data) {
            _this.chinas = data.data;
        });

        ajaxGet("/strategies/condition", {type:3}, function (data) {
            _this.themes = data.data;
        });

        ajaxGet("/strategies/themeCds", {}, function (data) {
            _this.themeCds = data.data;
        });

        ajaxGet("/banners/strategy", {}, function (data) {
            _this.commends = data.data;
        });


        //??????
        this.doPage(1);


































        /*



        ajaxGet("/strategies/themeCds",{}, function (data) {
            vue.themeCds = data.data;

        })




        ajaxGet("/banners/strategy",{}, function (data) {
            vue.commends = data.data;
        })

*/











        /*
                ajaxGet("/strategies/themeCds",{}, function (data) {
                    vue.themeCds = data.data;

                })
                ajaxGet("/banners/strategy",{}, function (data) {
                    vue.commends = data.data;
                })*/


    }
});

