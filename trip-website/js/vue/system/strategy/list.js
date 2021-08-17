var vue = new Vue({
    el:"#app",
    data:{
        page:{},
        themes:[],
        toasts:[]
    },
    methods:{
        themeSelect:function(themeId, event){
            $("._j_tag").removeClass("on")
            $(event.currentTarget).addClass("on");

            vue.doPage(1);
        },
        doPage:function(page, aThemeId){
            var param = getParams();
            var themeId;
            if (aThemeId === undefined) {
                themeId = $("._j_tag.on").data("tid");
            } else {
                themeId = aThemeId;
                //console.log($('#themeBtn3'))
            }
            ajaxGet("/strategies/query",{themeId:themeId, destId:param.destId, currentPage:page}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.current, vue.page.pages,vue.doPage);
            })
        }
    },
    mounted:function () {
        var param = getParams();
        var _this = this;
        //吐司
        ajaxGet("/destinations/toasts",{destId:param.destId}, function (data) {
            var list = data.data;
            vue.toasts = list;
        })


        ajaxGet("/strategies/themes", {destId:param.destId}, function (data) {
            _this.themes = data.data;
        })

        ajaxGet("/strategies/page", {destId:param.destId}, function (data) {
            _this.page = data.data;
        })

        this.doPage(1, param.themeId)

        this.$nextTick(function () {
            $('#themeBtn3').addClass("on")
            console.log("-------------")
            console.log($('#themeBtn3').context.all);
            console.log($('#themeBtn3'));
        })

    }
});

