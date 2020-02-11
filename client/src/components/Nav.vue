<template>
  <div class=" shadow w-full top-0">
    <ul class="flex justify-center py-4">
      <li class="mx-5"><router-link to="/home">Homepage</router-link></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>
