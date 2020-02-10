<template>
  <div id="app">
    <Nav class="z-40"></Nav>
    <router-view class="z-0"></router-view>
  </div>
</template>
<script>
import Nav from '@/components/Nav'
export default {
  components:{
    Nav
  }
}
</script>
