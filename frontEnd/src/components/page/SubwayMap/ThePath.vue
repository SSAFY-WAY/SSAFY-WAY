<template>
  <path class="path" stroke-width="3" :d="props.path"></path>
  <!-- <g class="path L1">
    <rect
      x="615"
      y="60"
      width="10"
      height="10"
      fill="#052f93"
      stroke="#052f93"
      stroke-linejoin="round"
      stroke-linecap="round"
      stroke-width="5"
    ></rect>
    <text
      x="620"
      y="68"
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
      1
    </text>
    <rect
      x="225"
      y="765"
      width="10"
      height="10"
      fill="#052f93"
      stroke="#052f93"
      stroke-linejoin="round"
      stroke-linecap="round"
      stroke-width="5"
    ></rect>
    <text
      x="230"
      y="773"
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
      1
    </text>
    <rect
      x="1385"
      y="945"
      width="10"
      height="10"
      fill="#052f93"
      stroke="#052f93"
      stroke-linejoin="round"
      stroke-linecap="round"
      stroke-width="5"
    ></rect>
    <text
      x="1390"
      y="953"
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
      1
    </text>
  </g> -->
</template>

<script setup>
const props = defineProps({
  path: String,
});
</script>

<style></style>
