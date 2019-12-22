<?php
require 'vendor/autoload.php';

use Respect\Validation\Validator as v;

$dotenv = new Dotenv\Dotenv(__DIR__);
$dotenv->load();

$access_key = getenv('ACCESS_KEY');
$secret_key = getenv('SECRET_KEY');
$associate_tag = getenv('ASSOCIATE_TAG');

$amazon = new Lib\Amazon($access_key, $secret_key, $associate_tag);
$search_indices = $amazon->getSearchIndices();

$items = [];
$keyword = '';
$search_index = '';
$has_searched = false;


if( ! ini_get('date.timezone') )
{
    date_default_timezone_set('UTC');
}


if (!empty($_GET['keyword'])) {
  $keyword = $_GET['keyword'];
  $search_index = $_GET['search_index'];
  $has_searched = true;

  $keyword_validator = v::alnum()
    ->noWhitespace()
    ->length(3, 80);
  if ($keyword_validator->validate($_GET['keyword']) && in_array($search_index, $search_indices)) {
    $response = $amazon->itemSearch($keyword, $search_index);
    $items = $response->Items->Item;
  } 
}

$loader = new Twig_Loader_Filesystem('./');
$twig = new Twig_Environment($loader, [
  'cache' => false,
  'auto_reload' => true
]);

$selected_filter = new Twig_SimpleFilter('selected', function ($string) {
  $exploded_string =  explode(',', $string);
  $selected_value = $exploded_string[0];
  $current_value = $exploded_string[1];
  return ($selected_value == $current_value) ? 'selected' : '';
});

$twig->addFilter($selected_filter);

$template = $twig->loadTemplate('amazonsearcher.html');
echo $template->render([
  'app_title' => getenv('APP_TITLE'),
  'search_indices' => $search_indices,
  'keyword' => $keyword,
  'search_index' => $search_index,
  'items' => $items,
  'has_searched' => $has_searched
]);