<template>
  <div class="min-h-screen" style="background-color: #f8f8fa;">
    <Nav></Nav>
    <router-view></router-view>
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
