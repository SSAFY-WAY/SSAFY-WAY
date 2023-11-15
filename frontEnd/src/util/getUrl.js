function getImageUrl(name) {
  return new URL(`../assets/${name}`, import.meta.url).href;
}

export default getImageUrl;
