$(document).ready(function(){$(".main-nav").dynamicSubmenuSide(),window.menuFun={show:function(a){a||(a=$(".mobile-menu-area .active")),$("body").addClass("active-mobile-menu"),$("#menu-back-btn").addClass("active").siblings().removeClass("active")},hide:function(a){a||(a=$(".mobile-menu-area .active")),$("body").removeClass("active-mobile-menu"),$("#menu-show-btn").addClass("active").siblings().removeClass("active")}},$(".mobile-menu-area > i").on("click",function(a){"menu-show-btn"==$(this).attr("id")?menuFun.show($(this)):menuFun.hide($(this))}),$(".nav-overlay-bg").on("click",function(){menuFun.hide()}),function(){$(".player").each(function(){var a=($(this),{fileSrc:"",imageSrc:"",id:"",width:"100%",height:"100%",aspectratio:""}),b={fileSrc:$(this).data("file-sec")||a.fileSrc,imageSrc:$(this).data("image-src")||a.imageSrc,id:$(this).attr("id"),width:$(this).data("width")||a.width,height:$(this).data("height")||a.height,aspectratio:$(this).data("aspectratio")||a.aspectratio},c=jwplayer(b.id).setup({file:b.fileSrc,image:b.imageSrc,width:b.width,height:b.height,aspectratio:b.aspectratio});c.onPlay(function(a){$(this.container).find(".jwcontrolbar").show(),$(this.container).closest(".post-thumb-wrap").find(".post-meta-info").hide()}).onComplete(function(){$(this.container).find(".jwcontrolbar").hide(),$(this.container).closest(".post-thumb-wrap").find(".post-meta-info").show()})})}(),function(){var a=$(".thumb-slides-container");a.length>0&&(a.each(function(){$(this).owlCarousel({singleItem:!0,autoPlay:!0,stopOnHover:!0,slideSpeed:800,pagination:!0,transitionStyle:"fade"})}),$(".thumb-slides-controller a").click(function(a){a.preventDefault();var b=$(this).closest(".blog-post-thumb-container").children(".thumb-slides-container").data("owlCarousel");$(this).hasClass("left-arrow")?b.prev():b.next()}))}();var a=$(".matx-form-valid");a.length>0&&a.matxSubmitValidate(),$(".sec-full").css("min-height",$(window).height()+"px");var b=$(".mobile-nav li.menu-item-has-children > a"),c=$(".mobile-nav ul.sub-menu");c.addClass("submenu-hidden"),b.length>0&&b.on("click",function(a){a.preventDefault();var b=($(".mobile-nav ul.sub-menu:not(.submenu-hidden)"),$(this).closest("li.menu-item-has-children")),c=b.children("ul.sub-menu");c.slideToggle(200).toggleClass("submenu-hidden");var d=c.find("ul.sub-menu"),e=d.parent("li.menu-item-has-children");e.removeClass("children-open"),d.hasClass("submenu-hidden")||d.addClass("submenu-hidden").hide(),c.hasClass("submenu-hidden")?b.removeClass("children-open"):b.addClass("children-open")})}),$(window).load(function(){$("#loader").fadeOut(),new WOW({offset:150,mobile:!1}).init(),/Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent)===!1&&(window.skrolr=skrollr.init({smoothScrolling:!1,forceHeight:!1,easing:{wtf:Math.random,inverted:function(a){return 1-a}}}))});