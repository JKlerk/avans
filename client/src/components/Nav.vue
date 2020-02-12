<template>
  <div class="flex justify-center pt-5 py-10">
    <div class="w-2/3">
      <ul class="flex">
        <li class="text-2xl font-bold">Avans</li>
        <div class="flex my-auto flex-1 justify-end">
          <li class="mx-2 py-1 bg-purple-600 rounded text-white text-sm px-5">Bands</li>
          <li class="mx-2 py-1 bg-purple-600 rounded text-white text-sm px-5">Artists</li>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    msg: String
  }
}
</script>
