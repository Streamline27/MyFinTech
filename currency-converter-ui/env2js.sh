#!/bin/bash
if [ -n "$API_HOST" ]; then
  echo "window.RUNTIME_API_HOST = '$API_HOST'" > public/env.js;
fi