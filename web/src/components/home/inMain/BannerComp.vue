<template>
  <swiper :slidesPerView="1" :spaceBetween="30" :loop="true" :centeredSlides="true" :pagination="{
              clickable: true,
            }" :autoplay="{
      delay: 2500,
      disableOnInteraction: false,
    }" :navigation="true" :modules="modules">
                <swiper-slide v-for="(item,index) in pictures" :key="index"><img :src="item.url"
                        style="width:100%;" /></swiper-slide>
            </swiper>
</template>

<script>
    // Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Autoplay, Navigation, Pagination, A11y } from "swiper";
// Import Swiper styles
import 'swiper/css';
import "swiper/css/navigation";
import "swiper/css/pagination";
export default {
    name:'BannerComp',
    props:['pictures'],
    setup(){
        const modules = [Autoplay, Pagination, Navigation, A11y]
        return{
            modules
        }
    },
    components:{
        Swiper,
        SwiperSlide
    }
}
</script>

<style>

</style>