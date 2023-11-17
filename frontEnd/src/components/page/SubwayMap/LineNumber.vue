<template>
  <rect
    :x="props.locs[0]"
    :y="props.locs[1]"
    width="10"
    height="10"
    :fill="props.color"
    :stroke="props.color"
    stroke-linejoin="round"
    stroke-linecap="round"
    stroke-width="5"
  ></rect>
  <text
    :x="props.locs[2]"
    :y="props.locs[3]"
    lengthAdjust="spacingAndGlyphs"
    text-anchor="middle"
    style="
      fill: white;
      stroke: white;
      stroke-width: 0.6;
      font-size: 9px;
      font-weight: normal;
      line-height: 10px;
    "
  >
    {{ props.line }}
  </text>
</template>

<script setup>
const props = defineProps({
  locs: Array,
  color: String,
  line: Number,
});
</script>

<style></style>
